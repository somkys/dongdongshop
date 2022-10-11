package com.dongdongshop.controller;
import com.dongdongshop.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-10
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    //登录
    @RequestMapping("login")
    @ResponseBody
    public Result login(String pname , String pwd){
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(pname,pwd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.ER().setData("用户名不存在");
        } catch (IncorrectCredentialsException e){
            return Result.ER().setData("密码错误");
        }
        return Result.Ok();
    }

    //首页
    @RequestMapping("toindex")
    public String toindex(){
        return "/admin/index";
    }
}

