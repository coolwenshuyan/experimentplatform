package com.coolwen.experimentplatform.utils;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class CasUtils {

    /**
     * 这里主要用作获取当前用户信息
     *
     * @param session Shiro 的session
     * @return map
     */
    public static   Map<Object, Object> getUserInfo(Session session) {

        SimplePrincipalCollection collection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//        collection.asList().get(1) 获取到的当前用户信息，但是有编码不能直接人眼读取
        HashMap hashMap = (HashMap) collection.asList().get(1);

        Map<Object,Object> map = new HashMap<>();
        for (Object key : hashMap.keySet()) {

            map.put(key,hashMap.get(key));

        }
        return map;
    }

    /**
     * 用于获取当前月份
     *
     * @return String 当前月份 例如 19年11月这样显示的
     */
    public static String getThisMon() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * @param time 时间戳
     * @return 时间字符串
     */
    public static String timeStamp2Date(String time) {
        if (time == null){
            return "";
        }
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param time 时间字符串
     * @return 时间字符串
     */
    public static long date2TimeStamp(String time) {
        if (time == null){
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date;
        try {
            date = sdf.parse(time);

            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public static Date getcurrentTime() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date;
        date = sdf.parse(sdf.format(System.currentTimeMillis()));
        return date;
    }
}
