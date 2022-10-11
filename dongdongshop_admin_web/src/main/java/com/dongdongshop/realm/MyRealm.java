package com.dongdongshop.realm;
import com.dongdongshop.entity.Person;
import com.dongdongshop.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @DubboReference
    IPersonService iPersonService;

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
        Person person = iPersonService.getPersonByName(token.getUsername());

        if (Objects.isNull(person)) {
            return null;
        }
        return new SimpleAuthenticationInfo(person,person.getPwd(), ByteSource.Util.bytes(person.getSalt()),person.getPname());
    }
}
