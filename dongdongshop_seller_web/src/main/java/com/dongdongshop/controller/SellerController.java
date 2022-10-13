package com.dongdongshop.controller;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.exctption.LoginException;
import com.dongdongshop.service.ISellerService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Objects;
import static com.dongdongshop.enume.ResuleMenu.*;

/**
 * <p>
 * 商家表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-13
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

    @DubboReference
    ISellerService iSellerService;

    @RequestMapping("toregister")
    public String toregister() {
        return "register";
    }

    @RequestMapping("tologin")
    public String tologin() {
        return "shoplogin";
    }

    @RequestMapping("loginsuccess")
    @ResponseBody
    public String loginsuccess() {
        return "登录成功";
    }

    @RequestMapping("login")
    @ResponseBody
    public Result login(String loginname, String loginpwd) {
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
        } catch (LoginException e){
            return new Result(e.getCode(),e.getMsg());
        }
        return new Result(SHIRO_LOGIN_SUCCESS.getCode(), SHIRO_LOGIN_SUCCESS.getMsg());
    }

    @RequestMapping("getCountByName")
    @ResponseBody
    public Result getCountByName(String sellerId) {
        if (StringUtils.isEmpty(sellerId)) {
            return Result.ER();
        }
        Long count = iSellerService.getCountByName(sellerId);
        if (count > 0) {
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("insertSeller")
    @ResponseBody
    public Result insertSeller(Seller seller) {
        //盐值
        String s = ShiroUtils.generateSalt(6);

        seller.setSalt(s);
        //md5
        seller.setPassword(ShiroUtils.encryptPassword("MD5", seller.getPassword(), s, 3));

        boolean save = iSellerService.save(seller);

        if (!save) {
            return new Result(SHIRO_REGISTER_ERROE.getCode(), SHIRO_REGISTER_ERROE.getMsg());
        }
        return new Result(SHIRO_REGISTER_SUCCESS.getCode(), SHIRO_REGISTER_SUCCESS.getMsg());
    }


}
