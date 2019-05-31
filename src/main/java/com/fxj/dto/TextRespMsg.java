package com.fxj.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fxj.constant.WechatRespMsgTypeConstant;

public class TextRespMsg extends RespMsg{

    private String text;

    @Override
    public String toString() {
        return "TextRespMsg{" +
                "text='" + text + '\'' +
                ", ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }

    public TextRespMsg(JSONObject reqMsg, String text) {
        super(reqMsg.getString("FromUserName"), WechatRespMsgTypeConstant.TEXT);
        this.text = text;
    }


    @JacksonXmlProperty(localName = "Content")
    @JacksonXmlCData
    public String getText() {
        return text;
    }

}
