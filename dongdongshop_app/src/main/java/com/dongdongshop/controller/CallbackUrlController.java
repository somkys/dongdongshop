package com.dongdongshop.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.dongdongshop.config.AlipayConfig;
import com.dongdongshop.entity.Order;
import com.dongdongshop.service.IOrderItemService;
import com.dongdongshop.service.IOrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class CallbackUrlController {

    @DubboReference
    IOrderItemService iOrderItemService;

    @DubboReference
    IOrderService iOrderService;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @RequestMapping("returnUrl")
    public String returnUrl(HttpServletRequest request , Model model) throws Exception {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            model.addAttribute("out_trade_no",out_trade_no);
            model.addAttribute("trade_no",trade_no);
            model.addAttribute("total_amount",total_amount);

            return "paysuccess";
        }else {
           return "payfail";
        }
    }

    @RequestMapping("notifyUrl")
    @ResponseBody
    public String notifyUrl(HttpServletRequest request,String out_trade_no , String trade_no,String trade_status){
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功

            System.out.println("异步回调方法");
            System.out.println("交易状态:"+trade_status);
            System.out.println("支付宝流水:"+trade_no);
            System.out.println("商户订单号:"+out_trade_no);


            //交易关闭
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知

                //交易成功
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                String out_biz_no = request.getParameter("out_biz_no"); //退款的请求号.itemID

                if (StringUtils.isEmpty(out_biz_no)){
                    //支付成功

                    //支付价格
                    String invoice_amount = request.getParameter("invoice_amount");

                    System.out.println("当前支付价格 = " + invoice_amount);

                    //当前积分,去掉小数
                    String intNumber = invoice_amount.substring(0,invoice_amount.indexOf("."));

                    //得到最终积分
                    Integer score = Integer.parseInt(intNumber);

                    JSONObject jsonObject = new JSONObject();
                    //订单号
                    jsonObject.putOpt("out_trade_no",out_trade_no);
                    //流水号
                    jsonObject.putOpt("trade_no",trade_no);
                    //价格
//                    jsonObject.putOpt("invoice_amount",invoice_amount);
                    //积分
                    jsonObject.putOpt("score",intNumber);

                    String jsonString = jsonObject.toString();

                    //添加积分,参数:订单号,流水号,金额,积分 用户Id
                    Message<String> message = MessageBuilder.withPayload(jsonString).build();

                    Order order = new Order();
                    order.setTradeNum(out_trade_no);
                    order.setTradeLiushui(trade_no);
                    order.setStatus("2");

                    //发送消息修改订单状态
                    //参数含义:  1，topic  2,消息  3,修改订单要用的参数,这个不是给消费者用的,是本地事务需要用到的
                    //尝试发送消息
                    TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("score_topic1", message, order);

                    System.out.println("result = " + result);

                      //添加order表流水号,以订单号为条件
//                    iOrderService.updateLiushuiById(out_trade_no,2,trade_no);

//                    //添加item表流水号,以订单号为条件
//                    iOrderItemService.updateLiushuiById(out_trade_no,2,trade_no);

                    System.out.println("这里是付款---------------");

                }else {
                    //退款成功

                    //添加item表流水号,以ID号为条件
                    iOrderItemService.updateLiushuiByIdd(Long.valueOf(out_biz_no),6,trade_no);

                    System.out.println("这里是退款----------------");

                }

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            return "success";

        }else {//验证失败
            return "fail";

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
    }
}
