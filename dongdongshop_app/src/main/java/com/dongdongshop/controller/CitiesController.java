package com.dongdongshop.controller;


import com.dongdongshop.entity.Cities;
import com.dongdongshop.service.ICitiesService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 行政区域地州市信息表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Controller
@RequestMapping("/cities")
public class CitiesController {

    @DubboReference
    ICitiesService iCitiesService;

    @RequestMapping("city")
    @ResponseBody
    public Result city(String provinceid){
      List<Cities> cities = iCitiesService.city(provinceid);
      return Result.Ok().setData(cities);
    }

}
