package com.dongdongshop.controller;
import com.dongdongshop.entity.Order;
import com.dongdongshop.entity.User;
import com.dongdongshop.service.IOrderService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import static com.dongdongshop.constants.RedisConstants.CART_REDIS_KEY;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-24
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @DubboReference
    IOrderService iOrderService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("togetOrderInfo")
    public String togetOrderInfo(){
        return "getOrderInfo";
    }

    @RequestMapping("insertOrder")
    @ResponseBody
    public Result insertOrder(Order order){
        //获取当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        if (user == null){
            return Result.ER();
        }

        order.setUserId(user.getUsername());

        order.setBuyerNick(user.getNickName());

        //购物车数据
        List<String> cartList = stringRedisTemplate.opsForList().range(CART_REDIS_KEY + user.getId(), 0, -1);

        iOrderService.insertOrder(order,cartList);

        return Result.Ok();
    }





}
