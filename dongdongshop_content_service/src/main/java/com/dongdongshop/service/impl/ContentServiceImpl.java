package com.dongdongshop.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.Content;
import com.dongdongshop.entity.ContentCategory;
import com.dongdongshop.mapper.ContentMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.page.PageResult;
import com.dongdongshop.service.IContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-18
 */
@Service
@DubboService
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //查询首页轮播图
    @Override
    public List<Content> carousel() {
        String key = "carousel:lunbotu";
        //查询redis缓存
        List<String> stringList = new ArrayList<>();

         stringList = stringRedisTemplate.opsForList().range(key, 0, -1);
        //判断缓存是否命中
        if (!stringList.isEmpty()){
            //命中直接返回
            List<Content> contents = new ArrayList<>();
            for (String s : stringList) {
                Content content = JSONUtil.toBean(s, Content.class);
                contents.add(content);
            }
            return contents;
        }
        //未命中查询数据库
        List<Content> contents = baseMapper.selectList(new QueryWrapper<Content>().eq("category_id",1).last("limit 3"));
        //添加redis缓存
        for (Content content : contents) {
            String s = JSONUtil.toJsonStr(content);
            stringList.add(s);
        }
        stringRedisTemplate.opsForList().rightPushAll(key,stringList);
        return contents;
    }

    //查询今日推荐
    @Override
    public List<Content> getToday() {
        String key = "carousel:today";
        //查询redis缓存
        List<String> stringList = new ArrayList<>();

        stringList = stringRedisTemplate.opsForList().range(key, 0, -1);
        //判断缓存是否命中
        if (!stringList.isEmpty()){
            //命中直接返回
            List<Content> contents = new ArrayList<>();
            for (String s : stringList) {
                Content content = JSONUtil.toBean(s, Content.class);
                contents.add(content);
            }
            return contents;
        }
        //未命中查询数据库
        List<Content> contents = baseMapper.selectList(new QueryWrapper<Content>().eq("category_id",2).last("limit 4"));
        //添加redis缓存
        for (Content content : contents) {
            String s = JSONUtil.toJsonStr(content);
            stringList.add(s);
        }
        stringRedisTemplate.opsForList().rightPushAll(key,stringList);
        return contents;
    }

    @Override
    public List<Content> getLike() {
        String key = "carousel:like";
        List<String> stringList = new ArrayList<>();
        stringList = stringRedisTemplate.opsForSet().randomMembers(key, 6);
        if (!stringList.isEmpty()){
            List<Content> contents = new ArrayList<>();
            for (String s : stringList) {
                Content content = JSONUtil.toBean(s, Content.class);
                contents.add(content);
            }
            return contents;
        }
        //查询数据库
        //未命中查询数据库
        List<Content> contents = baseMapper.selectList(new QueryWrapper<Content>().eq("category_id",3));

        //添加redis并返回6条数据
        List<Content> contentList = new ArrayList<>();
        for (int i = 0; i < contents.size(); i++) {
            String s = JSONUtil.toJsonStr(contents.get(i));
            stringRedisTemplate.opsForSet().add(key,s);
            if (i<6){
                contentList.add(contents.get(i));
            }
        }
        return contentList;
    }
}
