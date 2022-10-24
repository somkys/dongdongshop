package com.dongdongshop.controller;
import com.dongdongshop.entity.User;
import com.dongdongshop.service.IUserService;
import com.dongdongshop.util.ShiroUtils;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Objects;
import static com.dongdongshop.enume.ResuleMenu.SHIRO_LOGIN_SUCCESS;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-20
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @DubboReference
    IUserService iUserService;

    @RequestMapping("tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("register")
    @ResponseBody
    public Result register(User user,String code){
        //盐值
        String s = ShiroUtils.generateSalt(6);
        //设置盐值
        user.setSalt(s);
        //md5
        user.setPassword(ShiroUtils.encryptPassword("MD5", user.getPassword(), s, 3));
        String register = iUserService.register(user, code);
        if (!Objects.equals("注册成功",register)){
            return Result.ER().setData(register);
        }
        return Result.Ok();
    }

    @RequestMapping("login")
    @ResponseBody
    public Result login(String loginname , String loginpwd){
        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(loginname, loginpwd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return Result.ER().setData("用户名不存在");
        } catch (IncorrectCredentialsException e) {
            return Result.ER().setData("密码错误");
        }
        return new Result(SHIRO_LOGIN_SUCCESS.getCode(), SHIRO_LOGIN_SUCCESS.getMsg());
    }


}
