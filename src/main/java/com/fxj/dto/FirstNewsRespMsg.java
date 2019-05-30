package com.fxj.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fxj.constant.WechatRespMsgTypeConstant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FirstNewsRespMsg extends RespMsg{

    private Integer articleCount;
    private List<Article> articles;

    public FirstNewsRespMsg(JSONObject reqMsg) {
        super(reqMsg.getString("FromUserName"), WechatRespMsgTypeConstant.NEWS);
        articlesInit();
    }

    private void articlesInit() {
//        articles = new ArrayList<>();
//        File file = new File("other/text");
//        BufferedReader fileReader = new BufferedReader(new FileReader(new File(file,"firstList.txt")));
//        String s = null;
//        while ((s = fileReader.readLine())!=null){
//            Article article = new Article(null,null,null,null);
//
//            System.out.println(s);
//        }
        articleCount = articles.size();
    }

    @JacksonXmlProperty(localName = "ArticleCount")
    @JacksonXmlCData
    public Integer getArticleCount() {
        return articleCount;
    }

    @JacksonXmlProperty(localName = "Articles")
    @JacksonXmlCData
    public List<Article> getArticles() {
        return articles;
    }
}
