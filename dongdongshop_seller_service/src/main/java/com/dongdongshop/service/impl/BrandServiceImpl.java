package com.dongdongshop.service.impl;
import com.dongdongshop.dklog.DKtest;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.mapper.BrandMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.IBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-10
 */
@Service
@DubboService
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Autowired
    DKtest dKtest;

    @Override
    public PageResult selectBrandPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Brand> list = baseMapper.selectList(null);
        PageInfo info=new PageInfo<>(list);
        PageResult result = new PageResult(info.getPageNum(),info.getPageSize(), info.getList(), info.getTotal(), info.getPages());
        return result;
    }


}
