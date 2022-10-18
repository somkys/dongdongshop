package com.dongdongshop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 文件上传oss处理类接口
 */
public interface OssService {
    /**
     * 上传头像到oss
     * @param file 文件对象
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
