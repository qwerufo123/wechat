package com.fxj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fxj.component.UserLocation;
import com.fxj.constant.*;
import com.fxj.dto.Article;
import com.fxj.dto.ImageRespMsg;
import com.fxj.dto.NewsRespMsg;
import com.fxj.dto.TextRespMsg;
import com.fxj.mapper.CheckRecordMapper;
import com.fxj.mapper.UserMapper;
import com.fxj.po.CheckRecord;
import com.fxj.po.User;
import com.fxj.service.UserService;
import com.fxj.service.WechatRequestService;
import com.fxj.vo.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WechatRequestServiceImpl implements WechatRequestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserLocation userLocation;

    @Autowired
    private CheckRecordMapper checkRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Object handle(JSONObject reqMsg){

        //获取消息类型
        @NotBlank String msgType = reqMsg.getString("MsgType");
        switch (msgType){
            case WechatReqMsgTypeConstant.TEXT:
                logger.info("receive {}", WechatReqMsgTypeConstant.TEXT);
                return textHandle(reqMsg);
            case WechatReqMsgTypeConstant.IMAGE:
                logger.info("receive {}", WechatReqMsgTypeConstant.IMAGE);
                break;
            case WechatReqMsgTypeConstant.VOICE:
                logger.info("receive {}", WechatReqMsgTypeConstant.VOICE);
                break;
            case WechatReqMsgTypeConstant.VIDEO:
                logger.info("receive {}", WechatReqMsgTypeConstant.VIDEO);
                break;
            case WechatReqMsgTypeConstant.SHORT_VIDEO:
                logger.info("receive {}", WechatReqMsgTypeConstant.SHORT_VIDEO);
                break;
            case WechatReqMsgTypeConstant.LOCATION:
                logger.info("receive {}", WechatReqMsgTypeConstant.LOCATION);
                break;
            case WechatReqMsgTypeConstant.LINK:
                logger.info("receive {}", WechatReqMsgTypeConstant.LINK);
                break;
            case WechatReqMsgTypeConstant.EVENT:
                logger.info("receive {}", WechatReqMsgTypeConstant.EVENT);
                return  eventHandle(reqMsg);
            default:
                logger.warn("it doesn't match msg type");
        }
        return new TextRespMsg(reqMsg,"对不起，我还没学会这项技能");
    }

    /**
     * 接收文本信息处理
     * @param reqMsg
     * @return
     */
    private Object textHandle(JSONObject reqMsg) {
        String content = reqMsg.getString("Content");
        List<Article> list = null;
        Article article = null;
        switch (content){
            case WechatReqTextConstant.LIST:
                logger.info("receive {}", WechatReqTextConstant.LIST);
                return new TextRespMsg(reqMsg,RespTextConstant.FIRSTLIST);
            case WechatReqTextConstant.TECHNIQUE:
                logger.info("receive {}", WechatReqTextConstant.TECHNIQUE);
                return new TextRespMsg(reqMsg,RespTextConstant.TECHNIQUE);
            case WechatReqTextConstant.ER:
                logger.info("receive {}", WechatReqTextConstant.ER);
                return new ImageRespMsg(reqMsg,RespTextConstant.ERMEDIAID);
            case WechatReqTextConstant.FLOWCHART:
                logger.info("receive {}", WechatReqTextConstant.FLOWCHART);
                return new ImageRespMsg(reqMsg,RespTextConstant.FLOWCHARTMEDIAID);
            case WechatReqTextConstant.API:
                logger.info("receive {}", WechatReqTextConstant.API);
                list = new ArrayList<>();
                article = new Article("", "点击前往", "", RespTextConstant.APIURL);
                list.add(article);
                return new NewsRespMsg(reqMsg,list);
            case WechatReqTextConstant.DEPLOY:
                logger.info("receive {}", WechatReqTextConstant.DEPLOY);
                logger.info("receive {}", WechatEventKeyConstant.DEPLOY);
                list = new ArrayList<>();
                article = new Article("", "点击前往", "", RespTextConstant.DEPLOYURL);
                list.add(article);
                return new NewsRespMsg(reqMsg,list);
        }
        return new TextRespMsg(reqMsg,"对不起，我还没学会这项技能");
    }

    /**
     * 事件头处理
     * @param reqMsg
     * @return
     */
    private Object eventHandle(JSONObject reqMsg){
        @NotBlank String event = reqMsg.getString("Event");
        switch (event){
            case WechatEventConstant.SUBSCRIBE:
                logger.info("receive {}", WechatEventConstant.SUBSCRIBE);
                return userService.subscribe(reqMsg);
            case WechatEventConstant.UNSUBSCRIBE:
                logger.info("receive {}", WechatEventConstant.UNSUBSCRIBE);
                return userService.unsubscribe(reqMsg);
            case WechatEventConstant.SCAN:
                logger.info("receive {}", WechatEventConstant.SCAN);
                break;
            case WechatEventConstant.LOCATION:
                logger.info("receive {}", WechatEventConstant.LOCATION);
                return userService.putLocation(reqMsg);
            case WechatEventConstant.CLICK:
                logger.info("receive {}", WechatEventConstant.CLICK);
                return clickHandle(reqMsg);
            case WechatEventConstant.VIEW:
                logger.info("receive {}", WechatEventConstant.VIEW);
                break;
            default:
                logger.info("it doesn't match any event");
        }
        return  new TextRespMsg(reqMsg,"对不起，我还没学会这项技能");
    }

    /**
     * 点击事件头处理
     * @param reqMsg
     * @return
     */
    private Object clickHandle(JSONObject reqMsg) {
        String eventKey = reqMsg.getString("EventKey");
        List<Article> list = null;
        Article article = null;
        switch (eventKey) {
            case WechatEventKeyConstant.SHOWALL:
                logger.info("receive {}", WechatEventKeyConstant.SHOWALL);
                return new TextRespMsg(reqMsg,RespTextConstant.FIRSTLIST);
            case WechatEventKeyConstant.CHECKIN:
                logger.info("receive {}", WechatEventKeyConstant.CHECKIN);
                return checkin(reqMsg);
            case WechatEventKeyConstant.CHECKOUT:
                logger.info("receive {}", WechatEventKeyConstant.CHECKOUT);
                return checkout(reqMsg);
            case WechatEventKeyConstant.TECHNIQUE:
                logger.info("receive {}", WechatEventKeyConstant.TECHNIQUE);
                return new TextRespMsg(reqMsg,RespTextConstant.TECHNIQUE);
            case WechatEventKeyConstant.ER:
                logger.info("receive {}", WechatEventKeyConstant.ER);
                return new ImageRespMsg(reqMsg,RespTextConstant.ERMEDIAID);
            case WechatEventKeyConstant.FLOWCHART:
                logger.info("receive {}", WechatEventKeyConstant.FLOWCHART);
                return new ImageRespMsg(reqMsg,RespTextConstant.FLOWCHARTMEDIAID);
            case WechatEventKeyConstant.API:
                logger.info("receive {}", WechatEventKeyConstant.API);
                list = new ArrayList<>();
                article = new Article("", "API文档", "", RespTextConstant.APIURL);
                list.add(article);
                return new NewsRespMsg(reqMsg,list);
            case WechatEventKeyConstant.DEPLOY:
                logger.info("receive {}", WechatEventKeyConstant.DEPLOY);
                list = new ArrayList<>();
                article = new Article("", "部署文档", "", RespTextConstant.DEPLOYURL);
                list.add(article);
                return new NewsRespMsg(reqMsg,list);
            default:
                logger.info("it doesn't match any event key");
        }
        return new TextRespMsg(reqMsg,"对不起，我还没学会这项技能");
    }

    @Value("${check.latitude}")
    private Double latiude;
    @Value("${check.longitude}")
    private Double longitude;
    @Value("${check.distance}")
    private Double distance;


    /**
     * 签到
     * @param reqMsg
     * @return
     */
    @Transactional
    private Object checkin(JSONObject reqMsg) {
       double distance = addressToJudge(reqMsg);
        User user = userMapper.selectByPrimaryKey(reqMsg.getString("FromUserName"));
        if (distance < 0){
            return new TextRespMsg(reqMsg, RespTextConstant.NOADDRESS);
        }
        if (distance > this.distance) {
            return new TextRespMsg(reqMsg,RespTextConstant.NOSCOPE);
        }
        checkRecordMapper.insert(new CheckRecord(reqMsg.getString("FromUserName"), WorkingState.WORK.getValue()));
        user.setStatus(WorkingState.WORK.getValue());
        userMapper.updateByPrimaryKeySelective(user);
        return  new TextRespMsg(reqMsg,RespTextConstant.CHECKIN);
    }


    /**
     * 签退
     * @param reqMsg
     * @return
     */
    @Transactional
    private Object checkout(JSONObject reqMsg) {
        double distance = addressToJudge(reqMsg);
        User user = userMapper.selectByPrimaryKey(reqMsg.getString("FromUserName"));
        if (distance < 0){
            return new TextRespMsg(reqMsg, RespTextConstant.NOADDRESS);
        }
        if (distance > this.distance) {
            return new TextRespMsg(reqMsg,RespTextConstant.NOSCOPE);
        }
        checkRecordMapper.insert(new CheckRecord(reqMsg.getString("FromUserName"), WorkingState.OFF.getValue()));
        user.setStatus(WorkingState.OFF.getValue());
        userMapper.updateByPrimaryKeySelective(user);
        return  new TextRespMsg(reqMsg,RespTextConstant.CHECKOUT);
    }

    private double addressToJudge(JSONObject reqMsg) {
        String openid = reqMsg.getString("FromUserName");
        Location location = userLocation.get(openid);
        if (location==null && new Date().getTime() - location.getTime().getTime() < 1000*60*10 ){
            return -1;
        }
//        Coordinate lat = Coordinate.fromDegrees(latiude);
//        Coordinate lng = Coordinate.fromDegrees(longitude);
//        Point checkPosition = Point.at(lat, lng);
//
//        lat = Coordinate.fromDegrees(location.getLatitude());
//        lng = Coordinate.fromDegrees(location.getLongitude());
//        Point userPosition = Point.at(lat, lng);
//
//        return EarthCalc.harvesineDistance(checkPosition, userPosition); //in meters
        return 1;
    }

}
