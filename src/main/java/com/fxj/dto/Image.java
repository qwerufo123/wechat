package com.fxj.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class Image {

    private String mediaid;

    public Image(String mediaid) {
        this.mediaid = mediaid;
    }

    @JacksonXmlProperty(localName = "MediaId")
    @JacksonXmlCData
    public String getMediaid() {
        return mediaid;
    }

    @Override
    public String toString() {
        return "Image{" +
                "mediaid='" + mediaid + '\'' +
                '}';
    }
}
