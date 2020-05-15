package com.coolwen.experimentplatform.realm;

import com.coolwen.experimentplatform.model.Admin;
import com.coolwen.experimentplatform.service.AdminService;
import com.coolwen.experimentplatform.utils.LoginToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.realm
 * @ClassName: AdminRealm
 * @Author: Txc
 * @Description: 管理员权限
 * @Date: 2020/5/15 0015 13:19
 * @Version: 1.0
 */
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    AdminService adminService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("role:admin");
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        LoginToken loginToken = (LoginToken) token;
        Admin admin = adminService.findByUname(loginToken.getUsername());
        if (admin == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
