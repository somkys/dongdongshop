package com.dongdongshop.controller;

import com.dongdongshop.service.FreeService;
import com.dongdongshop.utils.Result;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("free")
public class FreeController {

    @Autowired
    FreeService freeService;

    @RequestMapping("item")
    @ResponseBody
    public Result item(Long goodsId){
        freeService.item(goodsId);
        return Result.Ok();
    }
}
