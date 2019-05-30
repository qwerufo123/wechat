package com.fxj.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fxj.constant.WechatRespMsgTypeConstant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewsRespMsg extends RespMsg{

    private Integer articleCount;
    private List<Article> articles;

    public NewsRespMsg(JSONObject reqMsg, List<Article> list) {
        super(reqMsg.getString("FromUserName"), WechatRespMsgTypeConstant.NEWS);
        articles = list;
        articleCount = list.size();
    }


    @JacksonXmlProperty(localName = "ArticleCount")
    @JacksonXmlCData
    public Integer getArticleCount() {
        return articleCount;
    }

    @JacksonXmlElementWrapper(localName = "Articles")
    @JacksonXmlProperty(localName = "item")
    @JacksonXmlCData
    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        return "NewsRespMsg{" +
                "articles=" + articles +
                ", ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }


}
