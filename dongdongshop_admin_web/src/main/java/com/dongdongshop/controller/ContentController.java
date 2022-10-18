package com.dongdongshop.controller;
import cn.hutool.json.JSONUtil;
import com.dongdongshop.entity.Content;
import com.dongdongshop.entity.ContentCategory;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.IContentCategoryService;
import com.dongdongshop.service.IContentService;
import com.dongdongshop.service.OssService;
import com.dongdongshop.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Smoky
 * @since 2022-10-18
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @DubboReference
    IContentService iContentService;

    @DubboReference
    IContentCategoryService iContentCategoryService;

    @Autowired
    OssService ossService;

    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(MultipartFile myFile){
        String s = ossService.uploadFileAvatar(myFile);
        System.out.println("s = " + s);
        return Result.Ok().setData(s);
    }

    @RequestMapping("/tocontent")
    public String tocontent(Model model){
        List<ContentCategory> list = iContentCategoryService.list();
        model.addAttribute("tbContentCategory",list);
        return "/admin/content";
    }

    @RequestMapping("toUpdateContent")
    @ResponseBody
    public Result toupdate(Long id){
        Content content = iContentService.getById(id);
        return Result.Ok().setData(content);
    }

    @ResponseBody
    @RequestMapping("/listContent")
    public Result listContent(){
        List<Content> list = iContentService.list();
        return Result.Ok().setData(list);
    }

    @RequestMapping("/updateContent")
    @ResponseBody
    public Result updateContent(Content content){
        boolean b = iContentService.updateById(content);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

    @ResponseBody
    @RequestMapping("/insertContent")
    public Result insertContent(Content content){
        content.setStatus("0");
        boolean save = iContentService.save(content);
        if (!save){
            return Result.ER();
        }
        return Result.Ok();
    }

    @RequestMapping("/deleteContent")
    @ResponseBody
    public Result deleteContent(Long id){
        boolean b = iContentService.removeById(id);
        if (!b){
            return Result.ER();
        }
        return Result.Ok();
    }

}
