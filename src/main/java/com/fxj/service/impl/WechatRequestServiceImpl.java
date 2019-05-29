package com.fxj.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fxj.component.UserLocation;
import com.fxj.constant.WechatEventConstant;
import com.fxj.constant.WechatEventKeyConstant;
import com.fxj.constant.WechatReqMsgTypeConstant;
import com.fxj.constant.WorkingState;
import com.fxj.dto.NewsRespMsg;
import com.fxj.dto.TextRespMsg;
import com.fxj.po.CheckRecord;
import com.fxj.service.UserService;
import com.fxj.service.WechatRequestService;
import com.fxj.vo.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
public class WechatRequestServiceImpl implements WechatRequestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserLocation userLocation;

//    @Autowired
//    private CheckRecordMapper checkRecordMapper;

    @Override
    public Object handle(JSONObject reqMsg){

        //获取消息类型
        @NotBlank String msgType = reqMsg.getString("MsgType");
        switch (msgType){
            case WechatReqMsgTypeConstant.TEXT:
                logger.info("receive {}", WechatReqMsgTypeConstant.TEXT);
                break;
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
        return "success";
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
        return  "success";
    }

    /**
     * 点击事件头处理
     * @param reqMsg
     * @return
     */
    private Object clickHandle(JSONObject reqMsg) {
        String eventKey = reqMsg.getString("EventKey");
        switch (eventKey) {
            case WechatEventKeyConstant.SHOWALL:
                logger.info("receive {}", WechatEventKeyConstant.SHOWALL);
                return new NewsRespMsg(reqMsg);
            case WechatEventKeyConstant.CHECKIN:
                logger.info("receive {}", WechatEventKeyConstant.CHECKIN);
                return checkin(reqMsg);
            case WechatEventKeyConstant.CHECKOUT:
                logger.info("receive {}", WechatEventKeyConstant.CHECKOUT);
                return checkout(reqMsg);
            default:
                logger.info("it doesn't match any event key");
        }
        return "success";
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
    private Object checkin(JSONObject reqMsg) {
       double distance = addressToJudge(reqMsg);
        if (distance < 0){
            return new TextRespMsg(reqMsg,"未获取位置");
        }
        if (distance > this.distance) {
            return new TextRespMsg(reqMsg,"请前往公司范围内再打卡");
        }
//        checkRecordMapper.insert(new CheckRecord(reqMsg.getString("FromUserName"), WorkingState.WORK.getValue()));
        return  new TextRespMsg(reqMsg,"打卡成功，祝新的一天工作愉快");
    }


    /**
     * 签退
     * @param reqMsg
     * @return
     */
    private Object checkout(JSONObject reqMsg) {
        double distance = addressToJudge(reqMsg);
        if (distance < 0){
            return new TextRespMsg(reqMsg,"未获取位置");
        }
        if (distance > this.distance) {
            return new TextRespMsg(reqMsg,"请前往公司范围内再打卡");
        }
//        checkRecordMapper.insert(new CheckRecord(reqMsg.getString("FromUserName"), WorkingState.OFF.getValue()));
        return  new TextRespMsg(reqMsg,"打卡成功，祝下班愉快");
    }

    private double addressToJudge(JSONObject reqMsg) {
//        String openid = reqMsg.getString("FromUserName");
//        Location location = userLocation.get(openid);
//        if (location==null){
//            return -1;
//        }
//        Coordinate lat = Coordinate.fromDegrees(latiude);
//        Coordinate lng = Coordinate.fromDegrees(longitude);
//        Point checkPosition = Point.at(lat, lng);
//
//        lat = Coordinate.fromDegrees(location.getLatitude());
//        lng = Coordinate.fromDegrees(location.getLongitude());
//        Point userPosition = Point.at(lat, lng);
//
//        return EarthCalc.harvesineDistance(checkPosition, userPosition); //in meters
        return 0;
    }

}
