package cn.xiaoyanol.crawler.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description: http请求工具类
 *
 * @Author: chenyanfeng
 * @Date: 2019-08-07
 * @Time: 上午9:28
 */
@Slf4j
public class HttpClientUtils {

    private static CloseableHttpClient httpClient;

//    private static JSONObject jsonObject = new JSONObject();

    /**
     * 信任SSL证书
     */
    static {
        try {
            SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL).loadTrustMaterial((x, y) -> true).build();
            RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setSSLContext(sslContext).setSSLHostnameVerifier((x, y) -> true).build();
        } catch (Exception e) {
            log.error("初始化HttpClientUtils工具类出现异常: {}", e.getMessage());
        }
    }
    /**
     * Get请求
     * @param url 请求路径
     * @param params 请求参数
     * @return
     */
    public static CloseableHttpResponse doGet(String url, Map<String, String> headers, Map<String, String> params) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {

            URIBuilder uriBuilder = new URIBuilder(url);

            // 设置url后面的参数
            setUrlParams(uriBuilder, params);

            HttpGet httpGet = new HttpGet(uriBuilder.build());

            // 设置请求头
            setHeaders(httpGet, headers);

            // 获取返回结果
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // 检查请求结果
            checkResponseCode(httpGet, response);

            return response;

        } catch (Exception e) {
            log.error("Get请求出现异常: {}", e.getMessage());
        }
        return null;
    }

    /**
     * Get请求
     * @param url 请求路径
     * @param params 请求参数
     * @return
     */
    public static T doGetForObject(String url, Map<String, String> headers, Map<String, String> params, Class<T> tClass) {
        try {

            CloseableHttpResponse response = doGet(url, headers, params);

            if (response == null) {
                return null;
            }

            String result = getStringByResponse(response);

//            return jsonObject.getObject(result, tClass);

        } catch (Exception e) {

            log.error("Get请求出现异常: {}", e.getMessage());
        }
        return null;
    }

    /**
     * Get请求
     * @param url 请求路径
     * @param params 请求参数
     * @return
     */
    public static String doGetForString(String url, Map<String, String> headers, Map<String, String> params) {
        try {

            CloseableHttpResponse response = doGet(url, headers, params);

            if (response == null) {
                return null;
            }

            String result = getStringByResponse(response);

            return result;

        } catch (Exception e) {

            log.error("Get请求出现异常: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 设置http请求头
     * @param request
     * @param headers
     */
    private static void setHeaders(HttpRequestBase request, Map<String, String> headers) {

        if (request == null || headers == null) {
            return;
        }

        Set<String> keySet = headers.keySet();
        for (String key : keySet) {
            request.setHeader(key, headers.get(key));
        }

    }

    /**
     * 设置url参数
     * @param uriBuilder
     * @param params
     */
    private static void setUrlParams(URIBuilder uriBuilder, Map<String, String> params) {
        if (uriBuilder == null || params == null) {
            return;
        }
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            uriBuilder.addParameter(key, params.get(key));
        }
    }

    /**
     * 检查是否正常响应
     * @param response
     */
    private static void checkResponseCode(HttpRequestBase request, CloseableHttpResponse response) {

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode != 200) {
            request.abort();
            throw new RuntimeException("Http 请求异常，响应码: " + statusCode);
        }
    }

    /**
     * 从请求结果中获取String
     * @param response
     * @return
     * @throws IOException
     */
    private static String getStringByResponse(CloseableHttpResponse response) throws IOException {

        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null) {
            result = EntityUtils.toString(entity, "utf-8");
        }
        EntityUtils.consume(entity);
        response.close();
        return result;
    }


    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient is error status code :"
                        + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求 发送json格式的报文 StringEntity
     * @param url
     * @param jsonString
     * @return
     */
    public static String doPost(String url, String jsonString) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(jsonString, "utf-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient is error status code :"
                        + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
