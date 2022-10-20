package com.dongdongshop.controller;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.IGoodsService;
import com.dongdongshop.service.ISellerService;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("sellerperson")
@Slf4j
public class SellerController {

    @DubboReference
    ISellerService iSellerService;

    @DubboReference
    IGoodsService iGoodsService;

    @Autowired
    RocketMQTemplate rocketMQTemplate;//模板类：建连接及断连结

    @RequestMapping("toseller1")
    public String toseller(){
        return "/admin/seller_1";
    }


    @RequestMapping("getPageSeller")
    @ResponseBody
    public Result getPageSeller(String name ,
                                String nickName,
                                @RequestParam(defaultValue = "1")Integer pageNum,
                                @RequestParam(defaultValue = "5")Integer pageSize){
        PageResult<Seller> pageResult = iSellerService.getPageSeller(pageNum, pageSize,name,nickName);
        return Result.Ok().setData(pageResult);
    }

    @RequestMapping("selectSellerById")
    @ResponseBody
    public Result selectSellerById(String sellerId){
       Seller seller= iSellerService.selectSellerById(sellerId);
       if (Objects.isNull(seller)){
           return Result.ER();
       }
       return Result.Ok().setData(seller);
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public Result updateStatus(String status , String sellerId){
       boolean b = iSellerService.updateStatus(status,sellerId);
       if (!b){
           return Result.ER();
       }
       return Result.Ok();
    }

    @RequestMapping("togoods")
    public String togoods(){
        return "/admin/goods";
    }


    @RequestMapping("listGoodsVo")
    @ResponseBody
    public Result listGoodsVo(String goodsName){
        List<GoodsVo> goodsVos = iGoodsService.listGoodsVo(goodsName);
       return Result.Ok().setData(goodsVos);
    }

    @RequestMapping("updateAuditStatus")
    @ResponseBody
    public Result updateAuditStatus(Long[] ids , String auditStatus){
       boolean b = iGoodsService.updateAuditStatus(ids,auditStatus);
       if (!b){
           return Result.ER();
       }
       if (Objects.equals("1",auditStatus)) {
           //尝试构造消息
           List<Long> longs = Arrays.asList(ids);
           String s = JSONUtil.toJsonStr(longs);
           //尝试发送消息
           rocketMQTemplate.convertAndSend("topic1",s);
           log.info("发送消息成功,消息为:{}",s);
       }
       return Result.Ok();
    }

}
