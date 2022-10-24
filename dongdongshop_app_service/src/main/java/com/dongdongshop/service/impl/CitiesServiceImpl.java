package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Cities;
import com.dongdongshop.mapper.CitiesMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.ICitiesService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行政区域地州市信息表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Service
@DubboService
public class CitiesServiceImpl extends ServiceImpl<CitiesMapper, Cities> implements ICitiesService {

    @Override
    public List<Cities> city(String provinceid) {
        return baseMapper.selectList(new QueryWrapper<Cities>().eq("provinceid",provinceid));
    }
}
