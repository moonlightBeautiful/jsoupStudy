package com.ims.c03getDomElementAttr;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author: GaoXu
 * @date: 2020/5/24
 * @desc：请对类进行描述
 */
public class App {

    public static void main(String[] args) throws IOException {
        // httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // httpPost
        HttpPost httpPost = new HttpPost("https://www.cnblogs.com/");
        // 执行httpPost请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取httpPost请求返回的httpEntity
        HttpEntity httpEntity = response.getEntity();
        // 解析httpEntity
        String pageContent = EntityUtils.toString(httpEntity, "utf-8");
        // 释放资源response关闭 httpClient关闭
        response.close();
        httpClient.close();
        //jsoup解析html内容
        Document doc = Jsoup.parse(pageContent);
        //通过选择器查找所有博客链接DOM
        Elements linkElements = doc.select("#post_list .post_item .post_item_body h3 a");
        for (Element e : linkElements) {
            System.out.println("博客标题：" + e.text());
            System.out.println("Html：" + e.toString());
            System.out.println("Html：" + e.html());
            System.out.println("博客地址：" + e.attr("href"));
            System.out.println("target：" + e.attr("target"));
        }
    }
}
