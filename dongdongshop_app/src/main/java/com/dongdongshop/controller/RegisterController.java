package com.dongdongshop.controller;
import com.dongdongshop.service.RegisterService;
import com.dongdongshop.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @Autowired
    RocketMQTemplate rocketMQTemplate;//模板类：建连接及断连结

    @RequestMapping("/toregister")
    public String toregister() {
        return "/register";
    }


    @RequestMapping("getMessageCode")
    @ResponseBody
    public Result getMessageCode(String phone) {
        //尝试发送异步消息
        rocketMQTemplate.convertAndSend("topic2",phone);
        log.info("发送消息成功,消息体为:{}",phone);
        return Result.Ok();
    }

}
