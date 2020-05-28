package com.ims.c02getDomElement;

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
        /**
         * ��������
         */
        //1.    ����tag��ȡ��ȡDOMԪ��
        Elements elements1 = doc.getElementsByTag("title");
        Element element1 = elements1.get(0);
        System.out.println("��ҳ�����ǣ�" + element1.text());
        //2.    ����id��ȡ��ȡDOMԪ��
        Element element2 = doc.getElementById("site_nav_top");
        System.out.println("�ںţ�" + element2.text());
        //3.    ����class��ȡ��ȡDOMԪ��
        Elements element3 = doc.getElementsByClass("post_item");
        for (Element e : element3) {
            System.out.println(e.html());
        }
        //4.    �������Ի�ȡ��ȡDOMԪ��
        Elements elements4 = doc.getElementsByAttribute("width");
        for (Element e : elements4) {
            System.out.println(e.toString());
        }
        //5.    ��������ֵ��ȡ��ȡDOMԪ��
        Elements elements5 = doc.getElementsByAttributeValue("target", "_blank");
        for (Element e : elements5) {
            System.out.println(e.toString());
        }

        /**
         * ѡ�����
         */
        //ͨ��ѡ�����������в�������DOM
        Elements linkElements = doc.select("#post_list .post_item .post_item_body h3 a");
        for (Element e : linkElements) {
            System.out.println("���ͱ��⣺" + e.text());
        }
        // ����href���Ե�aԪ��
        Elements hrefElements = doc.select("a[href]");
        for (Element e : hrefElements) {
            System.out.println(e.toString());
        }
        // ������չ��Ϊ.png��ͼƬDOM�ڵ�
        Elements imgElements = doc.select("img[src$=.png]");
        for (Element e : imgElements) {
            System.out.println(e.toString());
        }
    }
}
