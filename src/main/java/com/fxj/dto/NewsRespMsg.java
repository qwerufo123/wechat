package com.fxj.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fxj.constant.WechatRespMsgTypeConstant;

import java.util.ArrayList;
import java.util.List;

public class NewsRespMsg extends RespMsg{

    private Integer articleCount;
    private List<Article> articles;

    public NewsRespMsg(JSONObject reqMsg) {
        super(reqMsg.getString("FromUserName"), WechatRespMsgTypeConstant.NEWS);
        articlesInit();
    }

    private void articlesInit() {
        articles = new ArrayList<>();
        //TODO 查询展示列表
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
