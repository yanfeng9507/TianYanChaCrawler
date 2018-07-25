package cn.xiaoyanol.crawler;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description: 利用天眼查网站查询指定公司的工商信息
 * @Author: chenyanfeng
 * @Date: 2018-07-20
 * @Time: 下午5:09
 */
public class TianYanChaCrawler {
    public static void main(String[] args) throws IOException {


        //要获取信息的公司名单
        File companyFile = new File("company.txt");
        FileReader fileReader = new FileReader(companyFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> companyNameList = new ArrayList<String>();
        String companyName = null;
        while ((companyName = bufferedReader.readLine()) != null) {
            companyNameList.add(companyName.trim().replaceAll("　", ""));
        }
        bufferedReader.close();
        fileReader.close();

        //创建文件夹存储含有公司详细信息的html页面
        File directory = new File("info");
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            //过滤已经查询过的公司
            String[] fileNameList = directory.list();
            for (String fileName : fileNameList) {
                fileName = fileName.substring(0, fileName.length()-5);
                if (companyNameList.contains(fileName)) {
                    companyNameList.remove(fileName);
                    System.out.println(fileName+" 已经搜索过，该公司将被跳过。。。");
                    System.out.println();
                }
            }
        }

        if (companyNameList.size() == 0) {
            System.out.println("没有要搜索的公司，程序即将关闭。。。");
            System.exit(0);
        }else {
            System.out.println("程序将要搜索 "+ companyNameList.size()+" 个公司的信息。。。");
            System.out.println();
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpClientContext context = HttpClientContext.create();
        Scanner scanner = new Scanner(System.in);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).build();



        // httpClient.
        for (int index = 0; index < companyNameList.size(); index++) {
            companyName = companyNameList.get(index);
            System.out.println((index+1)+"、正在获取 "+ companyName +" 的信息。。。");
            System.out.println();
            HttpGet httpGet = new HttpGet("https://www.tianyancha.com/search?key=" + companyName);
            //设置默认请求头
            setHttpHeaders(httpGet);
            httpGet.setConfig(requestConfig);

            //执行HTTP请求
            HttpResponse response = httpClient.execute(httpGet, context);
            System.out.println("HTTP请求执行完成。。。");

            //判断是否出现机器人验证
            if (checkRobotVerification(response, context)) {
                List<URI> redirectLocations = context.getRedirectLocations();
                System.out.println("注意！出现机器人验证，请点击下面的链接，在验证完后输入 ok 继续运行。。。");
                System.out.println();
                System.out.println(redirectLocations.get(0));
                System.out.println();
                System.out.print("完成验证后，请在此处输入OK：");
                while (! "ok".equalsIgnoreCase(scanner.nextLine())) {
                    System.out.print("完成验证后，请在此处输入OK：");
                }
                System.out.println();
                //再次执行http请求
                response = httpClient.execute(httpGet, context);
                if (checkRobotVerification(response, context)) {
                    System.out.println("没有完成机器人验证，程序结束运行。。。。");
                    System.out.println();
                    break;
                }
            }

            System.out.println((index+1)+"、搜索 "+companyName+" 信息完成");
            System.out.println();
            HttpEntity entity = response.getEntity();

            //从搜索页面中提取目标详细信息的URL
            if (entity != null) {

                //将实体的内容转换为字符串
                String html= EntityUtils.toString(entity);
                Document document = Jsoup.parse(html);
                Elements select = document.select("a.name");
                //提取URL
                String url = select.attr("abs:href");

                //没有搜索结果时跳过
                if ("".equals(url)) {
                    System.out.println("无法在天眼查网站查询到："+companyName+" 的信息");
                    System.out.println();
                    try {
                        //系统暂停10s
                        System.out.println("系统暂停1秒。。。。");
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }else {
                    System.out.println((index+1)+"、获取 "+companyName+" 详情URL成功。。。");
                    System.out.println();
                }
                System.out.println("准备获取详细信息。。。");
                //获取公司的详细信息
                httpGet = new HttpGet(url);
                //设置默认请求头
                setHttpHeaders(httpGet);
                httpGet.setConfig(requestConfig);

                //执行HTTP请求
                response = httpClient.execute(httpGet, context);
                System.out.println("HTTP请求执行完成");

                //判断是否出现机器人验证
                if (checkRobotVerification(response, context)) {
                    List<URI> redirectLocations = context.getRedirectLocations();
                    System.out.println("注意！出现机器人验证，请点击下面的链接，在验证完后输入回车继续运行。。。");
                    System.out.println(redirectLocations.get(0));
                    System.out.print("完成验证后，请在此处输入OK：");
                    while (! "ok".equalsIgnoreCase(scanner.nextLine())) {
                        System.out.print("完成验证后，请在此处输入OK：");
                    }
                    System.out.println();
                    scanner.close();
                    //再次执行http请求
                    response = httpClient.execute(httpGet, context);
                    if (checkRobotVerification(response, context)) {
                        System.out.println("没有完成机器人验证。。。。");
                        break;
                    }
                }


                entity = response.getEntity();
                InputStream content = entity.getContent();
                FileOutputStream fileOutputStream = new FileOutputStream(directory+"/"+companyName+".html");
                byte[] buff = new byte[2048];
                int length = 0;
                //保存公司详细信息
                while ((length = content.read(buff, 0, buff.length)) != -1) {
                    fileOutputStream.write(buff, 0, length);
                }
                html = EntityUtils.toString(entity);
                fileOutputStream.close();
                content.close();

                //提取信息
                File file = new File("info/"+companyName+".html");
                document = Jsoup.parse(file, "UTF-8");
                Elements tbodys = document.select("tbody");
                if (tbodys.size() < 2){
                    System.out.println("注意！"+companyName+" 无法查询到工商信息。。。");
                    continue;
                }

                Element tbody = tbodys.get(1) ;

                Elements rows = tbody.select("tr");

                System.out.println((index+1)+"、"+companyName+" 的工商信息如下：");
                System.out.println();
                for (int i = 0; i < rows.size(); i++){
                    Elements tds = rows.get(i).select("td");
                    for (int j = 0; j < tds.size(); j++){
                        if (j % 2 == 0){
                            System.out.print(tds.get(j).text().split(" ")[0]+" : ");

                        }else {
                            System.out.print(tds.get(j).text()+"\t\t\t");
                        }
                    }
                    System.out.println();
                    System.out.println();

                }
            }
            System.out.println();
            System.out.println((index+1)+"、提取："+companyName+" 信息完成");
            System.out.println();
            if (index == companyNameList.size() - 1 ) {
                System.out.println("搜索完成，程序即将结束。。。");
            }else {
                int time = new Random().nextInt(2)+1;
                System.out.println("系统暂停：" + time + "秒");
                System.out.println();
                try {
                    Thread.sleep(time * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 检查是否出现机器人验证
     * @param response
     * @param context
     * @return
     */
    public static boolean checkRobotVerification(HttpResponse response , HttpClientContext context) {
        boolean result  = false;

        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            return true;
        }

        return result;
    }

    /**
     * 设置请求头
     * @param httpGet
     */
    public static void setHttpHeaders(HttpGet httpGet) {
        //设置默认请求头 在浏览器登陆后，把cookie的内容复制到这里设置cookie，不然无法查询
        httpGet.setHeader("Cookie", "");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "www.tianyancha.com");
        httpGet.setHeader("Referer", "https://www.tianyancha.com/");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
    }

    public static void UITips(HttpResponse response, HttpClientContext context) {

    }
}
