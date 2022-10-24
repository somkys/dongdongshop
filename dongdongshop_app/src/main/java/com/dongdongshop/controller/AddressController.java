package com.dongdongshop.controller;
import com.dongdongshop.entity.Address;
import com.dongdongshop.entity.User;
import com.dongdongshop.service.IAddressService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Controller
@RequestMapping("/addressController")
public class AddressController {

    @DubboReference
    IAddressService iAddressService;

    @RequestMapping("tohome")
    public String tohome(){
        return "home-index";
    }

    @RequestMapping("tohomeSettingAddress")
    public String tohomeSettingAddress(){
        return "home-setting-address";
    }

    @RequestMapping("getAddress")
    @ResponseBody
    public Result getAddress(){
        //获取当前登录用户信息
       User user = (User) SecurityUtils.getSubject().getPrincipal();
       if (user==null){
           return null;
       }
       List<Address> addresses = iAddressService.getAddress(user.getUsername());
       return Result.Ok().setData(addresses);
    }

    @RequestMapping("insertAddress")
    @ResponseBody
    public Result insertAddress(Address address){
        //获取当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        address.setUserId(user.getUsername());
        address.setIsDefault("0");
        boolean save = iAddressService.save(address);
        if (!save){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("deleteAddress")
    @ResponseBody
    public Result deleteAddress(Long id){
        boolean b = iAddressService.removeById(id);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("toUpdate")
    @ResponseBody
    public Result toUpdate(Long id){
        Address byId = iAddressService.getById(id);
        return Result.Ok().setData(byId);
    }

    @RequestMapping("updateAddress")
    @ResponseBody
    public Result updateAddress(Address address){
        boolean b = iAddressService.updateById(address);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("updateDefault")
    @ResponseBody
    public Result updateDefault(Long id){
        if (id == null){
            return Result.ER();
        }
        //获取当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        iAddressService.updateDefault(user.getUsername(),id);
        return Result.Ok();
    }


}
