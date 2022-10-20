package com.dongdongshop.controller;
import com.dongdongshop.entity.ContentCategory;
import com.dongdongshop.service.IContentCategoryService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * <p>
 * 内容分类 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-18
 */
@Controller
@RequestMapping("/contentcategory")
public class ContentCategoryController {

    @DubboReference
    IContentCategoryService iContentCategoryService;

    @RequestMapping("tocontentcategory")
    public String tocontentcategory(){
        return "/admin/content_category";
    }

    @RequestMapping("toUpdateTbContentCategory")
    @ResponseBody
    public Result toUpdateTbContentCategory(Long id){
        ContentCategory contentCategory = iContentCategoryService.getById(id);
        return Result.Ok().setData(contentCategory);
    }

    @RequestMapping("listContentcategory")
    @ResponseBody
    public Result listContentcategory(String name){
        List<ContentCategory> list = iContentCategoryService.listContentcategory(name);
        return Result.Ok().setData(list);
    }

    @RequestMapping("updateContentcategory")
    @ResponseBody
    public Result updateContentcategory(ContentCategory contentCategory){
        boolean b = iContentCategoryService.updateById(contentCategory);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("deleteContentcategory")
    @ResponseBody
    public Result deleteContentcategory(Long id){
        boolean b = iContentCategoryService.removeById(id);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("insertContentcategory")
    @ResponseBody
    public Result insertContentcategory(ContentCategory contentCategory){
        boolean save = iContentCategoryService.save(contentCategory);
        if (!save){
            return Result.ER();
        }
        return Result.Ok();
    }

}
