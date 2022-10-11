package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.entity.Specification;
import com.dongdongshop.entity.SpecificationOption;
import com.dongdongshop.mapper.SpecificationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.mapper.SpecificationOptionMapper;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.ISpecificationService;
import com.dongdongshop.utils.Result;
import com.dongdongshop.vo.SpecificationWithOpen;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 规格表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-11
 */
@Service
@DubboService
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {

    @Resource
    SpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageResult<Specification> selectSpecificationPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<Specification> list = baseMapper.selectList(null);

        PageInfo info = new PageInfo<>(list);

        PageResult result = new PageResult(info.getPageNum(), info.getPageSize(), info.getList(), info.getTotal(), info.getPages());

        return result;
    }

    @Override
    @Transactional
    public boolean insertSpecification(Specification specName, List<SpecificationOption> specificationOptions) {

        baseMapper.insertSpecificationNeedId(specName);

        return specificationOptionMapper.insertSpecificationAndOption(specName.getId(), specificationOptions);
    }

    @Override
    public SpecificationWithOpen toUpdate(Long id) {
        Specification specification = baseMapper.selectById(id);

        SpecificationWithOpen specificationWithOpen = new SpecificationWithOpen();

        BeanUtils.copyProperties(specification,specificationWithOpen);

        List<SpecificationOption> specificationOptions = specificationOptionMapper.selectList(new QueryWrapper<SpecificationOption>().eq("spec_id", id));

        specificationWithOpen.setSpecificationOptions(specificationOptions);

        return specificationWithOpen;
    }

    @Override
    @Transactional
    public boolean updateSpecificationWithOpen(Specification specName, List<SpecificationOption> specificationOptions) {
        //修改方法
        baseMapper.updateById(specName);

        List<SpecificationOption> specificationOptions1 = specificationOptionMapper
                .selectList(new QueryWrapper<SpecificationOption>().eq("spec_id", specName.getId()));

        List<Long> collect = specificationOptions1.stream()
                .map(specificationOption -> specificationOption.getId()).collect(Collectors.toList());

        int i = specificationOptionMapper.deleteBatchIds(collect);

        boolean b = specificationOptionMapper.insertSpecificationAndOption(specName.getId(), specificationOptions);

        return b;
    }

    @Override
    public boolean deleteSpecification(Long[] ids) {

        if (ids==null && ids.length<=0){
            return false;
        }

        List<Long> longs = Arrays.asList(ids);

        baseMapper.deleteBatchIds(longs);

        List<SpecificationOption> specificationOptions = specificationOptionMapper
                .selectList(new QueryWrapper<SpecificationOption>().in("spec_id", ids));

        List<Long> collect = specificationOptions.stream()
                .map(specificationOption -> specificationOption.getId()).collect(Collectors.toList());

        int i = specificationOptionMapper.deleteBatchIds(collect);

        if (i<=0){
            return false;
        }
        return true;
    }
}
