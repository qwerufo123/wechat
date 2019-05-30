package com.fxj.controller;

import com.alibaba.fastjson.JSONObject;
import com.fxj.service.WechatRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wechatInterface")
@EnableAutoConfiguration
public class WechatRequestController {

   @Autowired
   private WechatRequestService wechatRequestService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //是否为开发环境
//    @Value("${isexploit}")
//    private boolean isexploit;

    @PostMapping(value = "/incident",produces = { MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE })
    private Object incident(String signature,
                            Integer timestamp,
                            String nonce,
                            @RequestBody JSONObject reqMsg){
        Object object = null;
//        if (isexploit){//开发环境
//
//        }else{//上线环境
//            // TODO
//        }
        object = wechatRequestService.handle(reqMsg);
        return object;
    }

    @GetMapping("/incident")
    public String receive(@RequestParam String signature,
                          @RequestParam Integer timestamp,
                          @RequestParam String nonce,
                          @RequestParam String echostr) {
        logger.info("GET Request!!!");
        logger.info("signature: {}", signature);
        logger.info("timestamp: {}", timestamp);
        logger.info("nonce: {}", nonce);
        logger.info("echostr: {}", echostr);
        //todo verify with token
        return echostr;
    }

}
