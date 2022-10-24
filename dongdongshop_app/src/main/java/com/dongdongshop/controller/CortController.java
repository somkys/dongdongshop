package com.dongdongshop.controller;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.User;
import com.dongdongshop.service.CortService;
import com.dongdongshop.util.CookieUtils;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.ItemVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CortController {

    @Autowired
    CortService cortService;

    @RequestMapping("toCart")
    public String toCart(){
        return "cart";
    }

    @RequestMapping("addcart")
    public String addcart(HttpServletRequest request, HttpServletResponse response,Long itemId,Integer num){
        insertItemCookie(request,response,itemId,num);
        return "success-cart";
    }


    //加入购物车
//    @RequestMapping("insertItemCookie")
//    @ResponseBody
    public Result insertItemCookie(HttpServletRequest request, HttpServletResponse response,Long itemId,Integer num){

        //获取当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //当前用户未登录
        if (user==null){
            List<ItemVO> itemVOList = saveCookieCart(request, response, itemId, num);
            CookieUtils.setCookie(request,response,"cartList",JSONUtil.toJsonStr(itemVOList),true);
            return Result.Ok();
        }else {
            List<ItemVO> itemVOList = saveCookieCart(request, response, itemId, num);
            //当前用户已登录,获取到当前cookie的数据合并到redis中
            cortService.saveCartRedis(user.getId(), itemVOList);

            return Result.Ok();
        }
    }

    private  List<ItemVO> saveCookieCart(HttpServletRequest request, HttpServletResponse response,Long itemId,Integer num){

        Result<List<ItemVO>> itemByCookie = getItemByCookie(request, response);
        //获取到购物车数据
        List<ItemVO> itemVOList = itemByCookie.getData();

        itemVOList =  cortService.buileItemCookie(itemVOList,itemId,num);

        return itemVOList;
    }




    //获取购物车内容
    @RequestMapping("getItemByCookie")
    @ResponseBody
    public Result<List<ItemVO>> getItemByCookie(HttpServletRequest request, HttpServletResponse response){

        //获取当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        //如果当前用户为空,则查询Cookie数据返回
        if (user == null){
            List<ItemVO> cookie = getCookie(request);
            return Result.Ok().setData(cookie);
        }

        //查询redis数据
        List<ItemVO> rediscart = cortService.getCartByRedis(user.getId());

        if (rediscart.size()>0) {
            //查询redis数据返回,说明当前用户已登录,需要合并cookie和redis的数据
            //查询cookie数据
            List<ItemVO> cookie = getCookie(request);

            //合并redis和cookie购物车数据
            List<ItemVO> cartCookieWithReids = cortService.buildCartCookieWithRedis(cookie, rediscart);

            //删除Cookie购物车数据
            CookieUtils.deleteCookie(request, response, "cartList");

            return Result.Ok().setData(cartCookieWithReids);
        }

        return Result.Ok().setData(rediscart);


    }


    @RequestMapping("getCookie")
    @ResponseBody
    public List<ItemVO> getCookie(HttpServletRequest request){

            String cookieValue = CookieUtils.getCookieValue(request, "cartList",true);

            if (StringUtils.isEmpty(cookieValue)){
                cookieValue = "[]";
            }

            List<ItemVO> orderItems = JSONUtil.toList(cookieValue, ItemVO.class);

            return orderItems;
    }

    @RequestMapping("deleteCart")
    @ResponseBody
    public Result deleteCart(HttpServletRequest request,Long ItemId){

        return Result.Ok();
    }

}
