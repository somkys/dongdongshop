package com.dongdongshop.realm;

import com.dongdongshop.entity.Person;
import com.dongdongshop.entity.Seller;
import com.dongdongshop.exctption.LoginException;
import com.dongdongshop.service.IPersonService;
import com.dongdongshop.service.ISellerService;
import com.dongdongshop.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.dongdongshop.enume.ResuleMenu.*;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @DubboReference
    ISellerService iSellerService;

    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //这里是授权
        System.out.println("这里是授权");
        //创建对象,封装当前登录用户的角色，权限信息
        return null;
    }

    //自定义登陆认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1,获取用户身份信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Seller seller = iSellerService.getSellerByLoginname(token.getUsername());

        //未审核
        if (Objects.equals(seller.getStatus(), "0")) {
            throw new LoginException(SHIRO_LOGIN_ERROR.getMsg(), SHIRO_LOGIN_ERROR.getCode());
        }

        //审核未通过
        if (Objects.equals(seller.getStatus(), "2")) {
            throw new LoginException(SHIRO_LOGIN_ERROR_NO.getMsg(),SHIRO_LOGIN_ERROR_NO.getCode());
        }

        //店铺已关闭
        if (Objects.equals(seller.getStatus(), "3")) {
            throw new LoginException(SHIRO_LOGIN_ERROR_CLOST.getMsg(),SHIRO_LOGIN_ERROR_CLOST.getCode());
        }

        if (Objects.isNull(seller)) {
            return null;
        }
        return new SimpleAuthenticationInfo(seller,seller.getPassword(), ByteSource.Util.bytes(seller.getSalt()),seller.getSellerId());
    }
}
