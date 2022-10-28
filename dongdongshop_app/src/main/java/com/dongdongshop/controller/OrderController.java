package com.dongdongshop.controller;
import com.dongdongshop.entity.Order;
import com.dongdongshop.entity.User;
import com.dongdongshop.service.IOrderService;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.ItemVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
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

    @RequestMapping("toOrderSend")
    public String toOrderSend(String out_trade_no , String trade_no , Model model){
        model.addAttribute("out_trade_no",out_trade_no);
        model.addAttribute("trade_no",trade_no);
        return "home-order-send";
    }

    @RequestMapping("queryPay")
    @ResponseBody
    public Result queryPay(String WIDTQout_trade_no , String WIDTQtrade_no){
      List<ItemVO> itemVOList =  iOrderService.queryPay(WIDTQout_trade_no,WIDTQtrade_no);
      return Result.Ok().setData(itemVOList);

    }

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

        Map<String, String> map = iOrderService.insertOrder(order, cartList);

        map.put("WIDsubject",user.getId()+"订单");

        return Result.Ok().setData(map);
    }


}
