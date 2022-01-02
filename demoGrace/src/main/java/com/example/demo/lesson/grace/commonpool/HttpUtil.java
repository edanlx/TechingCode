package com.example.demo.lesson.grace.commonpool;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author seal email:876651109@qq.com
 * @date 2020/6/4 5:44 PM
 * @description
 */
@Slf4j
public class HttpUtil {

    /**
     * 发送get方法已改造成使用连接池
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(RequestConfig.custom()
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000)
                .build());
        CloseableHttpClient httpClient = null;
        try {
            httpClient = (CloseableHttpClient) HttpPoolManager.getInstance().borrowObject();
            try {
                @Cleanup CloseableHttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } catch (IOException e) {
                log.warn(String.format("%s:%s",
                        Thread.currentThread().getStackTrace()[1].getMethodName(),
                        e.getMessage()), e);
            }
        } catch (Exception e) {
            log.warn(String.format("%s:%s",
                    Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e.getMessage()), e);
        } finally {
            HttpPoolManager.getInstance().returnObject(httpClient);
        }
        return "";
    }

    /**
     * 发送post方法
     * @param url
     * @return
     */
    public static String sendPost(String url, Map<String, String> paramsMap, Map<String, String> headMap) {
        List<NameValuePair> formParams = new ArrayList<>();
        if (MapUtils.isNotEmpty(paramsMap)) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom()
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000)
                .build());
        if (MapUtils.isNotEmpty(headMap)) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "utf-8"));
            @Cleanup CloseableHttpClient httpClient = HttpClients.createDefault();
            @Cleanup CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            log.warn(String.format("%s:%s",
                    Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e.getMessage()), e);
        }
        return "";
    }

    /**
     * 发送postJson
     * @param url
     * @return
     */
    public static String sendPostJson(String url, String param, Map<String, String> headMap) {
        StringEntity entity = new StringEntity(param, "utf-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        entity.setContentEncoding("utf-8");
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom()
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(3000)
                .build());
        if (MapUtils.isNotEmpty(headMap)) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            httpPost.setEntity(entity);
            @Cleanup CloseableHttpClient httpClient = HttpClients.createDefault();
            @Cleanup CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entityResukt = response.getEntity();
            if (entityResukt != null) {
                return EntityUtils.toString(entityResukt);
            }
        } catch (IOException e) {
            log.warn(String.format("%s:%s",
                    Thread.currentThread().getStackTrace()[1].getMethodName(),
                    e.getMessage()), e);
        }
        return "";
    }

    /**
     * @author seal 876651109@qq.com
     * @date 2020/6/4 7:23 PM
     */
    public static String postFile(InputStream stream, String fileName, String requestUrl) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(true);
            conn.setChunkedStreamingMode(1024 * 10000);
            conn.setRequestProperty("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
            @Cleanup OutputStream out = new DataOutputStream(conn.getOutputStream());
            IOUtils.copy(stream, out);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String url = "http://www.baidu.com";
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("pool");
        for (int i = 0; i < 1000; i++) {
            sendGet(url);
        }
        stopWatch.stop();
        stopWatch.start("common");
        for (int i = 0; i < 1000; i++) {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(RequestConfig.custom()
                    .setConnectTimeout(3000)
                    .setConnectionRequestTimeout(3000)
                    .setSocketTimeout(3000)
                    .build());
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                httpclient.execute(httpGet).getEntity();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}