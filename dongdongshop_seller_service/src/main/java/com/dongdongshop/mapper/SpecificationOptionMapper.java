package com.dongdongshop.mapper;

import com.dongdongshop.entity.SpecificationOption;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规格选项表 Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-11
 */
public interface SpecificationOptionMapper extends BaseMapper<SpecificationOption> {

    boolean insertSpecificationAndOption(@Param("id") Long id, @Param("specificationOptions") List<SpecificationOption> specificationOptions);

}
