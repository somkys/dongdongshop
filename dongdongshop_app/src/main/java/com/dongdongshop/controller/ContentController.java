package com.dongdongshop.controller;
import com.dongdongshop.entity.Content;
import com.dongdongshop.service.IContentService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("content")
public class ContentController {

    @DubboReference
    IContentService iContentService;

    @RequestMapping("toindex")
    public String toindex(){
        return "index";
    }

    //查询首页轮播图
    @RequestMapping("carousel")
    @ResponseBody
    public Result carousel(){
       List<Content> contents = iContentService.carousel();
       return Result.Ok().setData(contents);
    }

    //查询今日推荐
    @RequestMapping("today")
    @ResponseBody
    public Result getToday(){
        List<Content> contents = iContentService.getToday();
        return Result.Ok().setData(contents);
    }

    //查询猜你喜欢
    @RequestMapping("like")
    @ResponseBody
    public Result getLike(){
        List<Content> contents = iContentService.getLike();
        return Result.Ok().setData(contents);
    }
}
