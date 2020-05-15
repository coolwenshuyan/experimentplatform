package com.coolwen.experimentplatform.utils.VerifyCode;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.utils.VerifyCode
 * @ClassName: VerifyCode
 * @Author: Txc
 * @Description: 验证码类
 * @Date: 2020/5/15 0015 17:22
 * @Version: 1.0
 */
public class VerifyCode {
    private String code;

    private byte[] imgBytes;

    private long expireTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
