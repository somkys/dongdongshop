package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Provinces;
import com.dongdongshop.mapper.ProvincesMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IProvincesService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 省份信息表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Service
@DubboService
public class ProvincesServiceImpl extends ServiceImpl<ProvincesMapper, Provinces> implements IProvincesService {

}
