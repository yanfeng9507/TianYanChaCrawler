package cn.xiaoyanol.crawler.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: chenyanfeng
 * @Date: 2018-11-23
 * @Time: 下午4:00
 */
public class HttpUtils {

    private static void setHttpHeaders(HttpGet httpGet) {
        //设置默认请求头 在浏览器登陆后，把cookie的内容复制到这里设置cookie，不然无法查询
        httpGet.setHeader("content-type", "application/json");
        httpGet.setHeader("x-auth-token", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTY2NzAyMDUwMSIsImlhdCI6MTU0MTY0NzEyMiwiZXhwIjoxNTQ0MjM5MTIyfQ.sW2MMsSoZxQlgaD5-Fu_XUXKv7H1_N0TSFHAG93Y2db0HzVKjZINt5sUU1w8nExXT6FATiTU1jDHNQFMhN0VFw");
        httpGet.setHeader("authorization", "0###oo34J0ZRgatN5UBO8UQRwap6Ew_A###1542956232943###24ed6f7b1512aee63869b97552a2bd8f");
        httpGet.setHeader("version", "TYC-XCX-WX");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.0; PRO 6 Plus Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/51.0.2074.203 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x2607033A) NetType/WIFI Language/zh_CN Process/appbrand2");
        httpGet.setHeader("Host", "api9.tianyancha.com");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("referer", "https://servicewechat.com/wx9f2867fc22873452/11/page-frame.html");
    }

    /**
     * http get 请求
     * @param url 请求URL
     * @param params 请求参数
     * @return
     * @throws IOException
     */
    public static HttpResponse get(String url, Map<String, String> params) throws IOException {
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).build();


        // 拼接请求URL
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        if (params != null && params.size() >= 0) {
            Set<String> keySet = params.keySet();
            boolean firstFlag = true;
            for (String key : keySet) {
                if (firstFlag) {
                    if ( StringUtils.isNotBlank(params.get(key)) ) {
                        sb.append("?" + key + "=" + params.get(key));
                        firstFlag = false;
                    }
                }else {
                    if ( StringUtils.isNotBlank(params.get(key))) {
                        sb.append("&" + key + "=" + params.get(key));
                    }
                }
            }
        }

        HttpGet httpGet = new HttpGet(sb.toString());
        //设置默认请求头
        setHttpHeaders(httpGet);
        httpGet.setConfig(requestConfig);

        //执行HTTP请求
        CloseableHttpClient httpClient = getHttpClient();
        HttpClientContext context = HttpClientContext.create();
        HttpResponse response = httpClient.execute(httpGet, context);

        return response;
    }

    public static CloseableHttpClient getHttpClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();

    }


}
