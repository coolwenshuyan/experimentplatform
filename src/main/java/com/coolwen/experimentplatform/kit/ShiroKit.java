package com.coolwen.experimentplatform.kit;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author CoolWen
 * @version 2018-10-31 9:19
 */
public class ShiroKit {
    //定义加密算法，可以自己定义
    public static String md5(String password, String salt) {
        String p = null;
        //hashlterations为散列算法几次
        p = new Md5Hash(password, salt, 1).toHex();
        return p;
    }

    public static boolean isEmpty(Object obj) {

        if (obj instanceof String) {
            return "".equals(obj);
        }

        if (obj instanceof Integer) {
            return (Integer) obj == 0;
        }
        if (obj == null) return true;
        else return false;
    }
}
