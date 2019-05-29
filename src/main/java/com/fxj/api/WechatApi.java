package com.fxj.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wechatapi" , url = "https://api.weixin.qq.com/cgi-bin")
public interface WechatApi {

    /**
     * 调用微信获取token接口
     * @param grant_type
     * @param appid
     * @param secret
     * @return
     */
    @GetMapping("/token")
    JSONObject getAccessToken(@RequestParam String grant_type,
                              @RequestParam String appid,
                              @RequestParam String secret);

    /**
     * 调用微信获取用户信息
     * @param access_token
     * @param openid
     * @param lang
     * @return
     */
    @GetMapping("/user/info")
    JSONObject getUserInfo(@RequestParam String access_token,
                           @RequestParam String openid,
                           @RequestParam String lang);


}
