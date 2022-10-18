package com.dongdongshop.controller;
import com.dongdongshop.service.OssService;
import com.dongdongshop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/oss")public class OssUploadTest {

    @Autowired
    OssService ossService;

    @ResponseBody
    @RequestMapping("/upload")
    public Result upload(@RequestParam("myFile") MultipartFile myFile){
        String s = ossService.uploadFileAvatar(myFile);
        System.out.println("s = " + s);
        return Result.Ok().setData(s);
    }
}
