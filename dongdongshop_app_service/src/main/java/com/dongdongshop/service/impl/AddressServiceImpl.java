package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Address;
import com.dongdongshop.mapper.AddressMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IAddressService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Service
@DubboService
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Override
    public List<Address> getAddress(String username) {
        List<Address> addresses = baseMapper.selectList(new QueryWrapper<Address>().eq("user_id", username));
        return addresses;
    }

    @Override
    public void updateDefault(String username , Long id) {
        baseMapper.updateDefault(username);
        baseMapper.updateDefaultTwo(id);
    }
}
