package com.dongdongshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongdongshop.entity.User;
import com.dongdongshop.mapper.UserMapper;
import com.dongdongshop.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.dongdongshop.constants.RedisConstants.REGISTER_CODE_KEY;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-20
 */
@Service
@DubboService
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public String register(User user, String code) {
        if (StringUtils.isEmpty(code)){
            return "请输入验证码";
        }

        User user1 = baseMapper.selectOne(new QueryWrapper<User>().eq("phone", user.getPhone()));

        if (user1 != null){
            return "该用户已注册";
        }

        String code1 = stringRedisTemplate.opsForValue().get(REGISTER_CODE_KEY + user.getPhone());

        if (StringUtils.isEmpty(code1)){
            return "验证码已失效";
        }

        if (!Objects.equals(code,code1)){
            return "验证码不正确";
        }
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        int insert = baseMapper.insert(user);
        if (insert<=0){
            return "注册失败";
        }

        return "注册成功";
    }

    @Override
    public User getUserByPhone(String phone) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("phone",phone));
    }

}
