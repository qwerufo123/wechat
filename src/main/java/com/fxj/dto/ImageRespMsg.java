package com.fxj.dto;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fxj.constant.WechatRespMsgTypeConstant;

public class ImageRespMsg extends RespMsg{

    private Image image;


    @Override
    public String toString() {
        return "ImageRespMsg{" +
                "image=" + image +
                ", ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime=" + CreateTime +
                ", MsgType='" + MsgType + '\'' +
                '}';
    }

    public ImageRespMsg(JSONObject reqMsg, String mediaid) {
        super(reqMsg.getString("FromUserName"), WechatRespMsgTypeConstant.IMAGE);
        image = new Image(mediaid);
    }

    @JacksonXmlProperty(localName = "Image")
    @JacksonXmlCData
    public Image getImage() {
        return image;
    }
}
