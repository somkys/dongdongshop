package com.dongdongshop.controller;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.IBrandService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-10
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @DubboReference
    IBrandService iBrandService;

    @PostMapping("ListBrand")
    @ResponseBody
    public Result brandList(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize){
        PageResult<Brand> pageResult = iBrandService.selectBrandPage(pageNum, pageSize);
        return Result.Ok().setData(pageResult);
    }

    @RequestMapping("insertBrand")
    @ResponseBody
    public Result insertBrand(Brand brand){
        boolean b = iBrandService.save(brand);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("updateBrand")
    @ResponseBody
    public Result updateBrand(Brand brand){
        boolean b = iBrandService.updateById(brand);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("getBrondById")
    @ResponseBody
    public Brand getBrondById(Integer id){
        Brand brand = iBrandService.getById(id);
        return brand;

    }

    @RequestMapping("deleteBrand")
    @ResponseBody
    public Result deleteBrand(Integer id){
        boolean b = iBrandService.removeById(id);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("toBrand")
    public String toBrand(){
        return "/admin/brand";
    }
}

