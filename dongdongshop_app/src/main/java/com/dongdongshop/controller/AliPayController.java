package com.dongdongshop.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.dongdongshop.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Objects;

@Controller
@RequestMapping("alipay")
public class AliPayController {

    /**
     *
     * @param WIDout_trade_no 商户订单号
     * @param WIDtotal_amount 付款金额
     * @param WIDsubject 订单名称
     * @param WIDbody 商品描述
     * @return
     */
    @RequestMapping("tradePagePay")
    @ResponseBody
    public String tradePagePay(String WIDout_trade_no,String WIDtotal_amount,String WIDsubject, String WIDbody){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDout_trade_no +"\","
                + "\"total_amount\":\""+ WIDtotal_amount +"\","
                + "\"subject\":\""+ WIDsubject +"\","
                + "\"body\":\""+ WIDbody +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
//        out.println(result);
        return result;
    }


    /**
     *
     * @param WTDTout_trade_no  订单号
     * @param WIDTQtrade_no    流水号
     * @return
     */

    //交易查询
    @RequestMapping("tradeQuery")
    public String tradeQuery(String WTDTout_trade_no, String WIDTQtrade_no, Model model){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        //商户订单号，商户网站订单系统中唯一订单号
//        String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
//        String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WTDTout_trade_no +"\","+"\"trade_no\":\""+ WIDTQtrade_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();

            JSONObject jsonObject = JSONUtil.parseObj(result);
            JSONObject response = (JSONObject) jsonObject.get("alipay_trade_query_response");
            String code = (String) response.get("code");

//            model.addAttribute("code",code);

            if (Objects.equals(code,"10000")){

                String msg = (String) response.get("msg");
                String out_trade_no = (String) response.get("out_trade_no");
//                System.out.println("out_trade_no = " + out_trade_no);
                String send_pay_date = (String) response.get("send_pay_date");
//                System.out.println("send_pay_date = " + send_pay_date);
                String total_amount = (String) response.get("total_amount");
//                System.out.println("total_amount = " + total_amount);
                String trade_no = (String) response.get("trade_no");


                model.addAttribute("code",code);
                model.addAttribute("send_pay_date",send_pay_date);
                model.addAttribute("out_trade_no",out_trade_no);
                model.addAttribute("total_amount",total_amount);
                model.addAttribute("trade_no",trade_no);
            }else {
                model.addAttribute("msg","交易查询失败");
            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        System.out.println("result = " + result);

        return "/alipay/queryPage";
    }


    /**
     * 退款
     * @param WIDTRout_trade_no 商户订单号
     * @param WIDTRtrade_no 支付宝交易号
     * @param WIDTRrefund_amount 需要退款的金额
     * @param WIDTRrefund_reason 退款的原因说明
     * @param WIDTRout_request_no 标识一次退款请求
     * @return
     */
    @RequestMapping("tradeRefund")
    @ResponseBody
    public String alipayTradeRefund(String WIDTRout_trade_no ,
                                    String WIDTRtrade_no ,
                                    String WIDTRrefund_amount ,
                                    String WIDTRrefund_reason ,
                                    String WIDTRout_request_no){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ WIDTRout_trade_no +"\","
                + "\"trade_no\":\""+ WIDTRtrade_no +"\","
                + "\"refund_amount\":\""+ WIDTRrefund_amount +"\","
                + "\"refund_reason\":\""+ WIDTRrefund_reason +"\","
                + "\"out_request_no\":\""+ WIDTRout_request_no +"\"}");

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println("result = " + result);
        //输出
       return "退款成功";
    }

}
