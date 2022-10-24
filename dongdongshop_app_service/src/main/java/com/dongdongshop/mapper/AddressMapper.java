package com.dongdongshop.mapper;

import com.dongdongshop.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
public interface AddressMapper extends BaseMapper<Address> {

    void updateDefault(String username);

    void updateDefaultTwo(Long id);

}
