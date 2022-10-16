package com.dongdongshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 商品表，SPU表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("togoodsedit")
    public String togoodsedit(){
        return "/admin/goods";
    }

}
