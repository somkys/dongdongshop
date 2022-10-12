package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.entity.TypeTemplate;
import com.dongdongshop.mapper.TypeTemplateMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.ITypeTemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 模板表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-12
 */
@Service
@DubboService
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateMapper, TypeTemplate> implements ITypeTemplateService {

    @Override
    public PageResult<TypeTemplate> selectBrandPage(Integer pageNum, Integer pageSize,String name) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<TypeTemplate> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        List<TypeTemplate> list = baseMapper.selectList(wrapper);
        PageInfo info=new PageInfo<>(list);
        PageResult result = new PageResult(info.getPageNum(),info.getPageSize(), info.getList(), info.getTotal(), info.getPages());
        return result;
    }
}
