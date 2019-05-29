package com.fxj.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "item")
public class Article {

    private String title;
    private String description;
    private String picUrl;
    private String url;

    public Article(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }

    @JacksonXmlProperty(localName = "Title")
    @JacksonXmlCData
    public String getTitle() {
        return title;
    }

    @JacksonXmlProperty(localName = "Description")
    @JacksonXmlCData
    public String getDescription() {
        return description;
    }

    @JacksonXmlProperty(localName = "PicUrl")
    @JacksonXmlCData
    public String getPicUrl() {
        return picUrl;
    }

    @JacksonXmlProperty(localName = "Url")
    @JacksonXmlCData
    public String getUrl() {
        return url;
    }
}
