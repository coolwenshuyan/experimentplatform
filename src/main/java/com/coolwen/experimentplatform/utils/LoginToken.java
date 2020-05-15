package com.coolwen.experimentplatform.utils;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.utils
 * @ClassName: LoginToken
 * @Author: Txc
 * @Description: 登录信息
 * @Date: 2020/5/15 0015 13:12
 * @Version: 1.0
 */
public class LoginToken extends UsernamePasswordToken {
    private String loginType;

    public LoginToken() {}

    public LoginToken(final String username, final String password,
                     final String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
