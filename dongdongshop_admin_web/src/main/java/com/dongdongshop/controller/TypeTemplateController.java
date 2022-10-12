package com.dongdongshop.controller;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.entity.Specification;
import com.dongdongshop.entity.TypeTemplate;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.IBrandService;
import com.dongdongshop.service.ISpecificationService;
import com.dongdongshop.service.ITypeTemplateService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Objects;
/**
 * <p>
 * 模板表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-12
 */
@Controller
@RequestMapping("/template")
public class TypeTemplateController {

    @DubboReference
    ITypeTemplateService iTypeTemplateService;

    //规格
    @DubboReference
    ISpecificationService iSpecificationService;

    //品牌
    @DubboReference
    IBrandService iBrandService;

    @RequestMapping("totemplate")
    public String totemplate(Model model) {
        List<Specification> list = iSpecificationService.list();
        model.addAttribute("specifications", list);
        List<Brand> list1 = iBrandService.list();
        model.addAttribute("brand", list1);
        return "/admin/type_template";
    }

    //分页查询
    @RequestMapping("getAllPage")
    @ResponseBody
    public Result getAllPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, String name) {
        PageResult<TypeTemplate> pageResult = iTypeTemplateService.selectBrandPage(pageNum, pageSize, name);
        return Result.Ok().setData(pageResult);
    }

    //增加
    @RequestMapping("insertTbTypeTemplate")
    @ResponseBody
    public Result insertTbTypeTemplate(TypeTemplate typeTemplate) {
        boolean save = iTypeTemplateService.save(typeTemplate);
        if (!save) {
            return Result.ER();
        }
        return Result.Ok();
    }

    //删除
    @RequestMapping("deleteTbTypeTemplate")
    @ResponseBody
    public Result deleteTbTypeTemplate(Long id) {
        boolean b = iTypeTemplateService.removeById(id);
        if (!b) {
            return Result.ER();
        }
        return Result.Ok();
    }

    //回显
    @RequestMapping("getByIdTemplate")
    @ResponseBody
    public Result getByIdTemplate(Long id){
        TypeTemplate typeTemplate = iTypeTemplateService.getById(id);
        if (Objects.isNull(typeTemplate)){
            return Result.ER();
        }
        return Result.Ok().setData(typeTemplate);
    }

    //修改
    @RequestMapping("updateTbTypeTemplate")
    @ResponseBody
    public  Result updateTbTypeTemplate(TypeTemplate typeTemplate){
        boolean b = iTypeTemplateService.updateById(typeTemplate);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }
}