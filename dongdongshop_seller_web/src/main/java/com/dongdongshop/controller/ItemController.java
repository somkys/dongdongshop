package com.dongdongshop.controller;


import com.dongdongshop.entity.Item;
import com.dongdongshop.service.IItemService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 商品表，SKU表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-17
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @DubboReference
    IItemService itemService;

    @RequestMapping("list")
    @ResponseBody
    public Result list(Long id){
        List<Item> itemByGoodsId = itemService.getItemByGoodsId(id);
        return Result.Ok().setData(itemByGoodsId);
    }
}
