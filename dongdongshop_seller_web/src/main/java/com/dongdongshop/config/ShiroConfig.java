package com.dongdongshop.config;

import com.dongdongshop.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ShiroConfig {

    @Resource
    MyRealm myRealm;

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //创建defaultWebSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //创建加密对象,并设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //2.1采用md5加密
        matcher.setHashAlgorithmName("MD5");
        //2.2采用迭代加密的次数
        matcher.setHashIterations(3);
        //将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(matcher);
        //将myrealm存入到创建defaultWebSecurityManager
        securityManager.setRealm(myRealm);
        //返回
        return securityManager;
    }


    //配置shiro内置过滤器
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/js/**","anon");//所有静态资源
        definition.addPathDefinition("/css/**","anon");//所有静态资源
        definition.addPathDefinition("/img/**","anon");//所有静态资源
        definition.addPathDefinition("/plugins/**","anon");
        definition.addPathDefinition("/seller/toregister","anon");
        definition.addPathDefinition("/seller/getCountByName","anon");
        definition.addPathDefinition("/seller/insertSeller","anon");
        definition.addPathDefinition("/seller/tologin","anon");
        definition.addPathDefinition("/seller/login","anon");
        definition.addPathDefinition("/**","authc");//拦截
        return definition;
    }

}

