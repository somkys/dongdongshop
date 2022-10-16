package com.dongdongshop.controller;
import com.dongdongshop.entity.ItemCat;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.entity.TypeTemplate;
import com.dongdongshop.service.IGoodsDescService;
import com.dongdongshop.service.IItemCatService;
import com.dongdongshop.service.ITypeTemplateService;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.GoodsWithGoodsEditVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * <p>
 * 商品详细表，SPU表 前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/goodsdesc")
public class GoodsDescController {

    @DubboReference
    IItemCatService itemCatService;

    @DubboReference
    ITypeTemplateService iTypeTemplateService;

    @DubboReference
    IGoodsDescService iGoodsDescService;

    @RequestMapping("togoodedit")
    public String togoodedit(){
        return "/admin/goods_edit";
    }

    //三级分类
    @RequestMapping("getItemList")
    @ResponseBody
    public Result getItemList(Long parentId){
        List<ItemCat> entities =  itemCatService.listWithTree(parentId);
        return Result.Ok().setData(entities);
    }

    //模板
    @RequestMapping("getTemById")
    @ResponseBody
    public Result getTemById(Long id){
        ItemCat itemCat = itemCatService.getById(id);
        TypeTemplate typeTemplate = iTypeTemplateService.getById(itemCat.getTypeId());
        return Result.Ok().setData(typeTemplate);
    }


    @RequestMapping("addGoodsAndDesc")
    @ResponseBody
    public Result addGoodsAndDesc(GoodsWithGoodsEditVO goodsWithGoodsEditVO){
        Seller seller = (Seller) SecurityUtils.getSubject().getPrincipal();
        //商家ID
        goodsWithGoodsEditVO.setSellerId(seller.getSellerId());

        boolean b = iGoodsDescService.addGoodsAndDesc(goodsWithGoodsEditVO);

       if (!b){
           return Result.ER();
       }
       return Result.Ok();
    }



}
