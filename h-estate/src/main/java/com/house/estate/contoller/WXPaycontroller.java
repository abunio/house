package com.house.estate.contoller;

import com.alibaba.fastjson.JSONObject;
import com.house.common.base.entity.AjaxResult;
import com.house.common.constant.WXPayConstant;
import com.house.common.utils.http.HttpClientUtil;
import com.house.common.utils.http.IpUtils;
import com.house.common.utils.number.AmountUtils;
import com.house.common.utils.spring.ServletUtils;
import com.house.common.utils.wechat.WXPayUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 微信支付demo
 * @Author huangW
 * @Date 9:24 2020/7/18
 * @Version 1.0
 */
@RestController
public class WXPaycontroller {

    @ApiOperation(value = "jsApi支付会员等级 ~", notes = "jsApi支付会员等级")
    @PostMapping("/pay")
    public AjaxResult payLevel(@RequestParam Long id, @RequestParam String code) {
        try {
            String openid = getOpenid(code);
            Map<String, String> paraMap = new HashMap<>();
            paraMap.put("appid", WXPayConstant.WX_LOGIN_APPID);
            paraMap.put("body", "ws_hh");
            paraMap.put("mch_id", WXPayConstant.WX_MCH_ID);
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
            paraMap.put("openid", openid);
            paraMap.put("out_trade_no", "12340");
            paraMap.put("spbill_create_ip", IpUtils.getIpAddr(ServletUtils.getRequest()));
            paraMap.put("total_fee", String.valueOf((int) AmountUtils.multiply(11.21, 100)));
            paraMap.put("trade_type", "JSAPI");
            paraMap.put("notify_url", "http://mc-test.wessyun.com/api/completePay");
            String sign = WXPayUtil.generateSignature(paraMap, WXPayConstant.WX_MCH_KEY);
            paraMap.put("sign", sign);
            String xml = WXPayUtil.mapToXml(paraMap);
            //统一下单接口
            String xmlStr = HttpClientUtil.sendPost(WXPayConstant.WX_UNIFIEDORDER_URL, xml);
            String prepay_id = "";
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
                prepay_id = map.get("prepay_id");
            }
            Map<String, String> payMap = new HashMap<>();
            payMap.put("appId", WXPayConstant.WX_LOGIN_APPID);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            String paySign = WXPayUtil.generateSignature(payMap, WXPayConstant.WX_MCH_KEY);
            payMap.put("paySign", paySign);
            return AjaxResult.success(payMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.error();
    }

    /**
     * 小程序登陆方式获取openid
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        // 配置请求参数
        Map<String, String> param = new HashMap<>();
        param.put("appid", WXPayConstant.WX_LOGIN_APPID);
        param.put("secret", WXPayConstant.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", WXPayConstant.WX_LOGIN_GRANT_TYPE);
        // 发送请求
        String wxResult = HttpClientUtil.doGet(WXPayConstant.WX_LOGIN_URL, param);
        JSONObject openIdStr = JSONObject.parseObject(wxResult);
        String openid = openIdStr.getString("openid");
        return openid;
    }

}
