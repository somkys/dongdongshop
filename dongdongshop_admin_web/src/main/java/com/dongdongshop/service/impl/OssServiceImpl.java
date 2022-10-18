package com.dongdongshop.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.dongdongshop.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Description: 上传文件到oss的处理类
 */
@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传头像到oss
     *
     * @param file 文件对象
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tE9fziWeZ2qtWUDan7y";
        String accessKeySecret = "JIhVhlX3x35MHrA2TOr6sZhjTM7ZWP";
        //块名
        String bucketName="smokyk-2204";
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件真实名称
            String originalFilename = file.getOriginalFilename();
            //重命名，防止相同文件出现覆盖 生成的f4f2e1a3-391a-4d5a-9438-0c9f5d27708=》需要替换成f4f2e1a3391a4d5a94380c9f5d27708c
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //新的文件名
            originalFilename=uuid+originalFilename;
            //2、把文件按照日期进行分类
            // 2021/6/30
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接 021/6/30/1.jpg
            originalFilename=datePath+"/"+originalFilename;
            // oss实现上传文件
            //第一个参数：Bucket名称
            //第二个参数：上传到oss文件路径和文件名称 /zhz/avatar.txt
            ossClient.putObject(bucketName, originalFilename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来->https://zhz-mail.oss-cn-beijing.aliyuncs.com/WechatIMG19.jpeg
            String url="https://"+bucketName+"."+endpoint+"/"+originalFilename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

