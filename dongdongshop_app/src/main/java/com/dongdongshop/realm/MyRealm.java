package com.dongdongshop.realm;
import com.dongdongshop.entity.User;
import com.dongdongshop.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @DubboReference
    IUserService iUserService;

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
        User user = iUserService.getUserByPhone(token.getUsername());

        //用户不存在
        if (user==null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),user.getUsername());
    }
}
