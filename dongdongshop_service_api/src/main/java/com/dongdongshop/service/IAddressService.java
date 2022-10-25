package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Address;
import com.dongdongshop.entity.Areas;
import com.dongdongshop.vo.AreaVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
public interface IAddressService extends IService<Address> {


    List<Address> getAddress(String username);

    void updateDefault(String username , Long id);

    List<AreaVO> getAddressVo(String username);

}
