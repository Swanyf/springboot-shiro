package com.swan.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /*
        创建ShiroFilterFactoryBean
    */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        
        // 关联设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();
        /*filterMap.put("/user/add","authc");
        filterMap.put("/user/update", "authc");*/
        // 表示user下面的所有资源都会被拦截
        filterMap.put("/index", "anon");

        // 授权过滤器，当前地址被拦截后，会自动跳转到未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/do/login", "anon");
        filterMap.put("/*", "authc");

        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");

        // 添加Shiro内置过滤器
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        // 被拦截后默认被拦截到login.jsp，修改默认跳转地址
        shiroFilterFactoryBean.setLoginUrl("/login");

        return shiroFilterFactoryBean;
    }
    

    /*
        创建DefaultWebSecurityManager
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        
        // 关联设置Realm
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    
    
    
    /*
        创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }
    
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
