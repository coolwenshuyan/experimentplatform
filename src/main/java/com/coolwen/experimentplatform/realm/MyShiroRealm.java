package com.coolwen.experimentplatform.realm;

import com.coolwen.experimentplatform.model.Resource;
import com.coolwen.experimentplatform.model.User;
import com.coolwen.experimentplatform.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 2016/6/4.
 * <p>
 * 权限登录与验证
 */
public class MyShiroRealm extends AuthorizingRealm {

    protected static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    @Lazy//解决shiro和事务管理不生效问题
    private UserService userService;

    //    用来判断授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("aaaaaaaaaa-------------->>>>>>>>>>>>");
        User user = (User) principals.getPrimaryPrincipal();
        int uid = user.getId();
        logger.debug("授权用户:" + user.getId() + "," + user.getUsername());
        List<String> roles = userService.listRoleSnByUser(uid);
        logger.debug("授权角色:" + roles);
        List<Resource> reses = userService.listAllResource(uid);
        List<String> permissions = new ArrayList<String>();
        logger.debug("授权资源:" + reses);
        for (Resource r : reses) {
            permissions.add(r.getUrl());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<String>(roles));
        info.setStringPermissions(new HashSet<String>(permissions));
        return info;
    }

    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
//        String username = String.valueOf(usernamePasswordToken.getUsername());
        String username = token.getPrincipal().toString();
        logger.debug("登录用户名username:" + username);
//        String password = new String(usernamePasswordToken.getPassword());
        String password = new String((char[]) token.getCredentials());
        logger.debug("登录用户名password:" + password);
        User user = userService.login(username, password);
        // 第一个参数 ，登陆后，需要在session保存数据
        // 第二个参数，查询到密码(加密规则要和自定义的HashedCredentialsMatcher中的HashAlgorithmName散列算法一致)
        // 第三个参数 ，realm名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        //该参数user.getUsername()为salt的值
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
        return info;
    }


    //清除认证
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        User u = (User) principals.getPrimaryPrincipal();
        SimplePrincipalCollection sp = new SimplePrincipalCollection(u.getUsername(), getName());
        Cache c = this.getAuthenticationCache();
        Set<Object> keys = c.keys();
        for (Object o : keys) {
            logger.debug("授权缓存:" + o + "-----" + c.get(o) + "----------");
        }
        super.clearCachedAuthenticationInfo(sp);
        //   super.clearCachedAuthenticationInfo(principals);
    }


    //清除权限
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {

        Cache c = this.getAuthorizationCache();
        Set<Object> keys = c.keys();
        for (Object o : keys) {
            logger.debug("认证缓存:" + o + "----------" + c.get(o) + "----------");
        }
//        User user = ((User) principals.getPrimaryPrincipal());
//        SimplePrincipalCollection spc = new SimplePrincipalCollection(user.getUsername(), getName());
        super.clearCachedAuthorizationInfo(principals);
    }

}
