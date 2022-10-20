package com.dongdongshop.controller;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.GoodsDesc;
import com.dongdongshop.entity.ItemCat;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.entity.TypeTemplate;
import com.dongdongshop.service.IGoodsDescService;
import com.dongdongshop.service.IItemCatService;
import com.dongdongshop.service.ISpecificationService;
import com.dongdongshop.service.ITypeTemplateService;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.GoodsWithGoodsEditVO;
import com.dongdongshop.vo.SpecificationWithOpen;
import com.dongdongshop.vo.TemplateVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
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

    @DubboReference
    ISpecificationService iSpecificationService;


    @RequestMapping("togoodedit")
    public String togoodedit(){
        return "/admin/goods_edit";
    }

    @RequestMapping("test")
    @ResponseBody
    public Result test(Long id){
        GoodsDesc byId = iGoodsDescService.getGoodsDescById(id);
        return Result.Ok().setData(byId);
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
        //获取规格字符串
        String specIds = typeTemplate.getSpecIds();

        List<SpecificationWithOpen> specificationWithOpens = JSONUtil.toList(specIds, SpecificationWithOpen.class);

        for (SpecificationWithOpen specificationWithOpen : specificationWithOpens) {
            SpecificationWithOpen vo = iSpecificationService.toUpdate(specificationWithOpen.getId());
            specificationWithOpen.setSpecificationOptions(vo.getSpecificationOptions());
        }

        TemplateVo templateVo = new TemplateVo();

        BeanUtils.copyProperties(typeTemplate,templateVo);

        templateVo.setSpecificationWithOpenList(specificationWithOpens);

        return Result.Ok().setData(templateVo);
    }


    @RequestMapping("addGoodsAndDesc")
    @ResponseBody
    public Result addGoodsAndDesc(GoodsWithGoodsEditVO goodsWithGoodsEditVO,String item){
        Seller seller = (Seller) SecurityUtils.getSubject().getPrincipal();
        //商家ID
        goodsWithGoodsEditVO.setSellerId(seller.getSellerId());
        //商家名称
        goodsWithGoodsEditVO.setSeller(seller.getName());

        boolean b = iGoodsDescService.addGoodsAndDesc(goodsWithGoodsEditVO,item);

        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }



}
