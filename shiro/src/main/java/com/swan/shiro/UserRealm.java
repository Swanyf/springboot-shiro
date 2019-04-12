package com.swan.shiro;

import com.swan.bean.User;
import com.swan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

   /**
    * 执行授权逻辑
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("执行授权逻辑");

        // 给资源进行授权
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.addStringPermission("user:add");

        /* 获取数据库的用户权限 */

        // 获取当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        // principal()由new SimpleAuthenticationInfo(user,user.getPassword(),"")中的user对象传递过去
        User user = (User) subject.getPrincipal();
        User userFromDb = userService.getUserById(user.getId());
        authorizationInfo.addStringPermission(userFromDb.getPermission());

        return authorizationInfo;
    }
    
    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

         /*
            Shiro判断逻辑
          */

         // arg0就是controller传递的token参数
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        User user = userService.getUserByName(token.getUsername());
        // 判断用户名
        if (user == null) {
            return null;    // 底层默认会抛出UnknownAccountException异常
        }

        // 判断数据库密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
}

    
    
}
