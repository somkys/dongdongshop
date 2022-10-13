package com.dongdongshop.controller;
import com.dongdongshop.entity.ItemCat;
import com.dongdongshop.service.IItemCatService;
import com.dongdongshop.service.ITypeTemplateService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-12
 */
@Controller
@RequestMapping("/itemCat")
public class ItemCatController {

    @DubboReference
    IItemCatService itemCatService;

    @DubboReference
    ITypeTemplateService iTypeTemplateService;

    @RequestMapping("selectParentIdInfo")
    @ResponseBody
    public Result selectParentIdInfo(Long ParentId){
        List<ItemCat> entities =  itemCatService.listWithTree(ParentId);
        return Result.Ok().setData(entities);
    }

    @RequestMapping("/toitemcat")
    public String toitemcat(Model model){
     model.addAttribute("typeTem", iTypeTemplateService.list());
        return "/admin/item_cat";
    }

    @RequestMapping("insertItemCat")
    @ResponseBody
    public Result insertItemCat(ItemCat itemCat){
        boolean save = itemCatService.save(itemCat);
        if (!save) {
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("getItemCatById")
    @ResponseBody
    public Result getItemCatById(Long id){
        ItemCat itemCat = itemCatService.getById(id);
        if (Objects.isNull(itemCat)){
            return Result.ER();
        }
        return Result.Ok().setData(itemCat);
    }

    @RequestMapping("updateItemCat")
    @ResponseBody
    public Result updateItemCat(ItemCat itemCat){
        boolean b = itemCatService.updateById(itemCat);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("deleteItemCat/{ids}")
    @ResponseBody
    public Result deleteItemCat(@PathVariable("ids") Long[] ids){
       boolean b = itemCatService.deleteItemCat(ids);
       if (!b){
           return Result.ER().setData("请先删除子节点");
       }
       return Result.Ok();
    }

}
