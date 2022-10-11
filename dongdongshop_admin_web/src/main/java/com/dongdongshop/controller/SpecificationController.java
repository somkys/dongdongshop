package com.dongdongshop.controller;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.Specification;
import com.dongdongshop.entity.SpecificationOption;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.ISpecificationService;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.SpecificationWithOpen;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 规格表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-11
 */
@Controller
@RequestMapping("/specification")
public class SpecificationController {

    @DubboReference
    ISpecificationService iSpecificationService;

    @RequestMapping("tospecification")
    public String tospecification() {
        return "/admin/specification";
    }

    @RequestMapping("listSpecification")
    @ResponseBody
    public Result listSpecification(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<Specification> pageResult = iSpecificationService.selectSpecificationPage(pageNum, pageSize);
        return Result.Ok().setData(pageResult);
    }

    @RequestMapping("insertSpecification")
    @ResponseBody
    public Result insertSpecification(Specification specName, String specOptionJson) {
        List<SpecificationOption> specificationOptions = JSONUtil.toList(specOptionJson, SpecificationOption.class);
        boolean b = iSpecificationService.insertSpecification(specName, specificationOptions);
        if (!b) {
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("toUpdate")
    @ResponseBody
    public Result toUpdate(Long id) {
        SpecificationWithOpen specification = iSpecificationService.toUpdate(id);
        if (Objects.isNull(specification)) {
            return Result.ER();
        }
        return Result.Ok().setData(specification);
    }

    @RequestMapping("updateSpecificationWithOpen")
    @ResponseBody
    public Result updateSpecificationWithOpen(Specification specName, String json) {
        List<SpecificationOption> specificationOptions = JSONUtil.toList(json, SpecificationOption.class);
        boolean b = iSpecificationService.updateSpecificationWithOpen(specName, specificationOptions);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("deleteSpecification")
    @ResponseBody
    public Result deleteSpecification(@RequestParam("ids[]") Long[] ids){
       boolean b= iSpecificationService.deleteSpecification(ids);
       if (!b){
           return Result.ER();
       }
       return Result.Ok();
    }


}
