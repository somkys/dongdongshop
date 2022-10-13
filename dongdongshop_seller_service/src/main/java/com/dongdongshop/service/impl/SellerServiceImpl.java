package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Brand;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.mapper.SellerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.ISellerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 商家表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-13
 */
@Service
@DubboService
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements ISellerService {

    @Override
    public Long getCountByName(String sellerId) {
        return baseMapper.selectCount(new QueryWrapper<Seller>().eq("seller_id",sellerId));
    }

    @Override
    public Seller getSellerByLoginname(String loginname) {
        return baseMapper.selectOne(new QueryWrapper<Seller>().eq("seller_id",loginname));
    }

    @Override
    public PageResult<Seller> getPageSeller(Integer pageNum, Integer pageSize,String name , String nickName) {
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Seller> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if (!StringUtils.isEmpty(nickName)){
            wrapper.like("nick_name",nickName);
        }

        List<Seller> list = baseMapper.selectList(wrapper);

        PageInfo info=new PageInfo<>(list);
        PageResult result = new PageResult(info.getPageNum(),info.getPageSize(), info.getList(), info.getTotal(), info.getPages());
        return result;
    }

    @Override
    public Seller selectSellerById(String sellerId) {
        return baseMapper.selectOne(new QueryWrapper<Seller>().eq("seller_id",sellerId));
    }

    @Override
    public boolean updateStatus(String status, String sellerId) {
        int count = baseMapper.updateStatus(status,sellerId);
        if (count<=0){
            return false;
        }
        return true;
    }

}
