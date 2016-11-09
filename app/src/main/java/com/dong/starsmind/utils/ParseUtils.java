package com.dong.starsmind.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by zengwendong on 16/11/8.
 */
public class ParseUtils {


    /**
     * 解析网页内容
     * http://dushu.m.baidu.com/rankdetail?cate=%E7%8E%84%E5%B9%BB&query=%E7%8E%84%E5%B9%BB%E7%83%AD%E9%97%A8%E6%A6%9C
     */
    public static void parseHTMLToBook() {
        //
        String url = "http://m.baidu.com/tcx?appui=alaxs&page=detail&gid=4294987752&ssid=0&from=1015097f&uid=0&pu=usm@2,sz@320_1001,ta@iphone_2_5.0_3_537&bd_page_type=1&baiduid=A31E8AF93F0307E90EC89148CC7F764C&tj=wise_novel_book_1_0_10_l1";

        try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            println("doc--->" + doc.toString());
            Elements contents = doc.getElementsByTag("script");
            List<Node> nodes = contents.last().childNodes();
            int size = nodes == null ? 0 : nodes.size();
            String value = "";
            for (int i = 0; i < size; i++) {
                Node node = nodes.get(i);
                Attributes attributes = node.attributes();
                if (attributes != null) {
                    List<Attribute> attributeList = attributes.asList();
                    for (int j = 0; j < attributeList.size(); j++) {
                        Attribute attribute = attributeList.get(j);
                        value = attribute.getValue();
                        println("attribute.getValue--->" + value);
                        break;
                    }
                }
            }

            Document document = Jsoup.parse(value);
            Elements elementsByClass = document.getElementsByClass("global-cut invoke");
            for (Element ele : elementsByClass) {
                println("目录--->" + ele.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void println(String str) {
        System.out.println(str);
    }

    /**
     * http://dushu.m.baidu.com/api/getRankDetailData?page=1&count=20&cate=玄幻&query=玄幻热门榜
     */
    public static void parseJSONToBook() {


    }

}
