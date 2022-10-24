package com.dongdongshop.controller;


import com.dongdongshop.entity.Provinces;
import com.dongdongshop.service.IProvincesService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 省份信息表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Controller
@RequestMapping("/provinces")
public class ProvincesController {
    @DubboReference
    IProvincesService iProvincesService;

    @RequestMapping("province")
    @ResponseBody
    public Result province(){
        List<Provinces> list = iProvincesService.list();
        return Result.Ok().setData(list);
    }
}
