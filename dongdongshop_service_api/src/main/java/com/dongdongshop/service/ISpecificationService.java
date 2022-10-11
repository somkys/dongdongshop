package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.entity.Specification;
import com.dongdongshop.entity.SpecificationOption;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.SpecificationWithOpen;

import java.util.List;

/**
 * <p>
 * 规格表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-11
 */
public interface ISpecificationService extends IService<Specification> {

    //分页查询
    PageResult<Specification> selectSpecificationPage(Integer pageNum, Integer pageSize);

    //添加方法
    boolean insertSpecification(Specification specName, List<SpecificationOption> specificationOptions);

    //根据id查询
    SpecificationWithOpen toUpdate(Long id);

    //修改
    boolean updateSpecificationWithOpen(Specification specName, List<SpecificationOption> specificationOptions);

    //批量删除
    boolean deleteSpecification(Long[] ids);

}
