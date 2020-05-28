package com.ims.c01hello;

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
 * @desc��������������
 */
public class App {

    public static void main(String[] args) throws IOException {
        // httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // httpPost
        HttpPost httpPost = new HttpPost("https://www.cnblogs.com/");
        // ִ��httpPost����
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // ��ȡhttpPost���󷵻ص�httpEntity
        HttpEntity httpEntity = response.getEntity();
        // ����httpEntity
        String pageContent = EntityUtils.toString(httpEntity, "utf-8");
        // �ͷ���Դresponse�ر� httpClient�ر�
        response.close();
        httpClient.close();

        //jsoup����html����
        Document doc = Jsoup.parse(pageContent);
        //1. ����tag��ȡ��ȡDOMԪ��
        Elements elements = doc.getElementsByTag("title");
        Element element = elements.get(0);
        System.out.println("��ҳ�����ǣ�" + element.text());
        //2. ����id��ȡ��ȡDOMԪ��
        Element element2 = doc.getElementById("site_nav_top");
        System.out.println("�ںţ�" + element2.text());
    }
}
