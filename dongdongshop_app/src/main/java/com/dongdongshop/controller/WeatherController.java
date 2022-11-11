package com.dongdongshop.controller;

import com.alibaba.fastjson.JSON;
import com.dongdongshop.config.HttpUtils;
import com.dongdongshop.config.MyAuthenticator;
import com.dongdongshop.utils.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("weather")
public class WeatherController {

    @RequestMapping("listWeather")
    @ResponseBody
    public Result listWeather() {
        String host = "https://weather01.market.alicloudapi.com";
        String path = "/area-to-weather-date";
        String method = "GET";
        String appcode = "9081c7bdb9784af9b17daf5ded755b2b";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("area", "北京");
//        querys.put("areaCode", "530700");
//        querys.put("areaid", "101291401");
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        querys.put("date", time);
//        querys.put("need3HourForcast", "1");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            return Result.Ok().setData(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("getArea")
    @ResponseBody
    public Result getArea(){
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://www.mxnzp.com/api/ip/self?app_id=qhtllynonlrhrtqd&app_secret=cFptdDFQS1kwcUNWaE80cDVoQ0FQZz09");
        CloseableHttpResponse response=null;
        try {
            response=client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            Result cityJson=null;
            if (statusCode==200){
                HttpEntity entity = response.getEntity();
                String Json = EntityUtils.toString(entity, "utf-8");
                cityJson = JSON.parseObject(Json, Result.class);
                System.out.println("cityJson = " + cityJson);
            }
            return cityJson;
        } catch (IOException e) {
            e.printStackTrace();
            return Result.ER();
        }finally {
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @RequestMapping("listWeather2")
    @ResponseBody
    public Result listWeather2(String city) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://apis.juhe.cn/simpleWeather/query?city="+city+"&key=83020109c2e575fdaad0996d84d05782");
        CloseableHttpResponse response=null;
        try {
            response=client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            Result cityWeatherJson=null;
            if (statusCode==200){
                HttpEntity entity = response.getEntity();
                String Json = EntityUtils.toString(entity, "utf-8");
                System.out.println("Json = " + Json);
                return Result.Ok().setData(Json);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.Ok();
    }

    @RequestMapping("sendQQMail")
    @ResponseBody
    public Result sendQQMail() throws Exception{
        //1.卫星
        Message message = null;

        //4.补充燃料
        Session session = null;

        //4.2
        Properties properties = null;
        properties = new Properties();
        //设置主机,端口,是否需要身份验证
        properties.put("mail.smtp.host","smtp.qq.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth","true");

        //4.1  //创建一个MyAuthenticator 用来实例化 Authenticator
        //tlfzjzzaptdjehcb
        session = Session.getDefaultInstance(properties,new MyAuthenticator("1940405375@qq.com","tlfzjzzaptdjehcb"));

        //3.实例化message
        message = new MimeMessage(session);

        //设置消息的内容 3.1
        //设置邮件主题
        message.setSubject("东东商城邮箱测试");
        //设置邮件内容
        message.setText("东东商城邮件发送测试...");
        //设置邮件发送方
        InternetAddress address = new InternetAddress("1940405375@qq.com");
        message.setFrom(address);
        //设置邮件的接受方
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("1940405375@qq.com"));

        //2.送卫星上天
        Transport.send(message);

        return Result.Ok();
    }
}
