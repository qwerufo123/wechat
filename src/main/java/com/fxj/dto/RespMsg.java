package com.fxj.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fxj.constant.WechatTokenConstant;

import java.util.Date;

public class RespMsg {
    protected String ToUserName;
    protected String FromUserName;
    protected Long CreateTime;
    protected String MsgType;

    public RespMsg(String toUserName, String msgType) {
        ToUserName = toUserName;
        CreateTime = new Date().getTime();
        MsgType = msgType;
    }

    @JacksonXmlProperty(localName = "ToUserName")
    @JacksonXmlCData
    public String getToUserName() {
        return ToUserName;
    }

    @JacksonXmlProperty(localName = "FromUserName")
    @JacksonXmlCData
    public String getFromUserName() {
        return WechatTokenConstant.exploitID;
    }

    @JacksonXmlProperty(localName = "CreateTime")
    public Long getCreateTime() {
        return CreateTime;
    }

    @JacksonXmlProperty(localName = "MsgType")
    @JacksonXmlCData
    public String getMsgType() {
        return MsgType;
    }}
