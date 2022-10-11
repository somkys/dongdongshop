package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.utils.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-10
 */
public interface IBrandService extends IService<Brand> {

    PageResult selectBrandPage(Integer pageNum, Integer pageSize);

}
