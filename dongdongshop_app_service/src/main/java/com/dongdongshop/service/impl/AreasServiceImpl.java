package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Areas;
import com.dongdongshop.mapper.AreasMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.service.IAreasService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行政区域县区信息表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-23
 */
@Service
@DubboService
public class AreasServiceImpl extends ServiceImpl<AreasMapper, Areas> implements IAreasService {

    @Override
    public List<Areas> listAreas(String cityid) {
        return baseMapper.selectList(new QueryWrapper<Areas>().eq("cityid",cityid));
    }
}
