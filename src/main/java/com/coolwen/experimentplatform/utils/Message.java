package com.coolwen.experimentplatform.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.utils
 * @ClassName: Message
 * @Author: Txc
 * @Description: 数据集
 * @Date: 2020/5/14 0014 12:26
 * @Version: 1.0
 */
public class Message extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Message() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static Message error() {
        return error(1, "操作失败");
    }

    public static Message error(String msg) {
        return error(500, msg);
    }

    public static Message error(int code, String msg) {
        Message message = new Message();
        message.put("code", code);
        message.put("msg", msg);
        return message;
    }

    public static Message ok(String msg) {
        Message message = new Message();
        message.put("msg", msg);
        return message;
    }

    public static Message ok(Map<String, Object> map) {
        Message message = new Message();
        message.putAll(map);
        return message;
    }

    public static Message ok() {
        return new Message();
    }

    @Override
    public Message put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
