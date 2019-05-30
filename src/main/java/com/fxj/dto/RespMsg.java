package com.fxj.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fxj.constant.WechatTokenConstant;

import java.util.Date;

@JacksonXmlRootElement(localName = "xml")
public class RespMsg {
    protected String ToUserName;
    protected String FromUserName = WechatTokenConstant.exploitID;
    protected Long CreateTime;
    protected String MsgType;

    @Override
    public String toString() {
        return "RespMsg{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }

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
