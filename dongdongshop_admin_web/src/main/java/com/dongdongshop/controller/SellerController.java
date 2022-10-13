package com.dongdongshop.controller;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.ISellerService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Objects;

@Controller
@RequestMapping("sellerperson")
public class SellerController {

    @DubboReference
    ISellerService iSellerService;

    @RequestMapping("toseller1")
    public String toseller(){
        return "/admin/seller_1";
    }


    @RequestMapping("getPageSeller")
    @ResponseBody
    public Result getPageSeller(String name ,
                                String nickName,
                                @RequestParam(defaultValue = "1")Integer pageNum,
                                @RequestParam(defaultValue = "5")Integer pageSize){
        PageResult<Seller> pageResult = iSellerService.getPageSeller(pageNum, pageSize,name,nickName);
        return Result.Ok().setData(pageResult);
    }

    @RequestMapping("selectSellerById")
    @ResponseBody
    public Result selectSellerById(String sellerId){
       Seller seller= iSellerService.selectSellerById(sellerId);
       if (Objects.isNull(seller)){
           return Result.ER();
       }
       return Result.Ok().setData(seller);
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public Result updateStatus(String status , String sellerId){
       boolean b = iSellerService.updateStatus(status,sellerId);
       if (!b){
           return Result.ER();
       }
       return Result.Ok();
    }

}
