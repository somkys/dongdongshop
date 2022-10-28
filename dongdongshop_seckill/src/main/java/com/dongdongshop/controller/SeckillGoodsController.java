package com.dongdongshop.controller;


import com.dongdongshop.entity.SeckillGoods;
import com.dongdongshop.entity.User;
import com.dongdongshop.service.ISeckillGoodsService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
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
 * @since 2022-10-27
 */
@Controller
@RequestMapping("/seckill")
public class SeckillGoodsController {

    @DubboReference
    ISeckillGoodsService iSeckillGoodsService;

    @RequestMapping("listSeckill")
    @ResponseBody
    public Result listSeckill(){
      List<SeckillGoods> seckillGoods = iSeckillGoodsService.listSeckill();

      if (seckillGoods.size()<=0){
          return Result.ER().setData("当前没有正在秒杀的商品");
      }

      return Result.Ok().setData(seckillGoods);
    }

    @RequestMapping("/getOneSeckillGoods")
    @ResponseBody
    public Result getOneSeckillGoods(Long id){
      SeckillGoods seckillGoods =  iSeckillGoodsService.getOneSeckillGoods(id);
      return Result.Ok().setData(seckillGoods);
    }


    @RequestMapping("seckillGoods")
    @ResponseBody
    public Result seckillGoods(Long id){
        //获取当前登录用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();


    }



}
