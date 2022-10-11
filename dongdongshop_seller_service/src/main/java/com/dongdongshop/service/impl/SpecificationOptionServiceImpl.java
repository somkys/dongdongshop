package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.SpecificationOption;
import com.dongdongshop.mapper.SpecificationOptionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 规格选项表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-11
 */
@Service
@DubboService
public class SpecificationOptionServiceImpl extends ServiceImpl<SpecificationOptionMapper, SpecificationOption> implements IService<SpecificationOption> {

}
