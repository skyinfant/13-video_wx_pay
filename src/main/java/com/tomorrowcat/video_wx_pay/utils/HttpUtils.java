package com.tomorrowcat.video_wx_pay.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装http get post
 */
public class HttpUtils {

    private static final Gson gson = new Gson();

    /**
     * get方法    http和https都能访问
     *
     * @param url
     * @return
     */
    public static Map<String, Object> doGet(String url) {
        Map<String, Object> map = new HashMap<>();
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//连接超时
                    .setSocketTimeout(5000)//请求超时
                    .setRedirectsEnabled(true) //允许自动重定向
                    .build();

            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResult = EntityUtils.toString(response.getEntity());
                map = gson.fromJson(jsonResult, map.getClass());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;

    }

    /**
     * 封装post       http和https都能访问
     * data：参数
     * @return
     */
    public static String doPost(String url, String data, int timeout) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout) //连接超时
                    .setConnectionRequestTimeout(timeout)//请求超时
                    .setSocketTimeout(timeout)
                    .setRedirectsEnabled(true)  //允许自动重定向
                    .build();


            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);

            httpPost.addHeader("Content-Type", "text/html;charset-UTF-8");
            //使用字符串传参
            if (data != null) {
                StringEntity stringEntity = new StringEntity(data, "utf-8");
                httpPost.setEntity(stringEntity); //传参
            }

            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(httpEntity);
                return result;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

//class Test {
//    public static void main(String[] args) {
//        Map<String, Object> map = HttpUtils.doGet("http://localhost:8080/api/v1/wechat/login_url?access_page=fasdlfa");
//        String str = HttpUtils.doPost("http://localhost:8080/api/v1/wechat/login_url?access_page=fasdlfa", "", 3000);
//        System.out.println("get请求："+map);
//        System.out.println("post请求："+str);
//    }
//}
//
