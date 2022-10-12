package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.TypeTemplate;
import com.dongdongshop.page.PageResult;

/**
 * <p>
 * 模板表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-12
 */
public interface ITypeTemplateService extends IService<TypeTemplate> {

    PageResult<TypeTemplate> selectBrandPage(Integer pageNum, Integer pageSize , String name);

}
