package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.ContentCategory;
import com.dongdongshop.page.PageResult;

import java.util.List;

/**
 * <p>
 * 内容分类 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-18
 */
public interface IContentCategoryService extends IService<ContentCategory> {

    List<ContentCategory> listContentcategory(String name);

}
