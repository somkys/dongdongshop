package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Content;
import com.dongdongshop.page.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-18
 */
public interface IContentService extends IService<Content> {


    List<Content> carousel();

    List<Content> getToday();

    List<Content> getLike();

}
