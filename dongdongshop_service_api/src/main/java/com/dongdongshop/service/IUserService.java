package com.dongdongshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongdongshop.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Smoky
 * @since 2022-10-20
 */
public interface IUserService extends IService<User> {

    String register(User user, String code);

    User getUserByPhone(String phone);
}
