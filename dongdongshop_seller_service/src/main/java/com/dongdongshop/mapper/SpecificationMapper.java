package com.dongdongshop.mapper;

import com.dongdongshop.entity.Specification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 规格表 Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-11
 */
public interface SpecificationMapper extends BaseMapper<Specification> {

    void insertSpecificationNeedId(@Param("specification") Specification specification);

}
