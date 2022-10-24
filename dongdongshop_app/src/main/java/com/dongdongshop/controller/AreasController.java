package com.dongdongshop.controller;


import com.dongdongshop.entity.Areas;
import com.dongdongshop.service.IAddressService;
import com.dongdongshop.service.IAreasService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.geom.Area;
import java.util.List;

/**
 * <p>
 * 行政区域县区信息表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Controller
@RequestMapping("/areas")
public class AreasController {

    @DubboReference
    IAreasService iAreasService;

    @RequestMapping("listAreas")
    @ResponseBody
    public Result areas(String cityid){
      List<Areas> areas = iAreasService.listAreas(cityid);
      return Result.Ok().setData(areas);
    }
}
