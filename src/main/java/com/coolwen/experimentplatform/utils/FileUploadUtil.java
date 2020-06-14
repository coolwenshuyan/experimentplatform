package com.coolwen.experimentplatform.utils;


import com.coolwen.experimentplatform.exception.UserException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CoolWen
 * @version 2019-07-06 17:20
 */
public class FileUploadUtil {
    private static final String dateFormat = "yyyy_MM_dd_HH_mm_SS_ssss";

    private static final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    public static String formatDate() {

        return sdf.format(new Date());
    }

    public static String picRename(String pic_type) {
        System.out.println("文件类型-------------------------" + pic_type);
        String file_ture_name = ".";
        if (pic_type.equals("image/jpeg")) {
            file_ture_name = file_ture_name.concat("jpg");
        } else if (pic_type.equals("image/png")) {
            file_ture_name = file_ture_name.concat("png");
        } else if (pic_type.equals("image/bmp")) {
            file_ture_name = file_ture_name.concat("bmp");
        } else if (pic_type.equals("image/gif")) {
            file_ture_name = file_ture_name.concat("gif");
        } else throw new UserException("上传图片类型不正确！");
        return FileUploadUtil.formatDate() + file_ture_name;
    }

    public static String fileRename(String pic_type) {
        System.out.println("文件类型-------------------------" + pic_type);
        String file_ture_name = ".";
        if (pic_type.equals("xls")) {
            file_ture_name = file_ture_name.concat("xls");
        } else if (pic_type.equals("xlsx")) {
            file_ture_name = file_ture_name.concat("xlsx");
        } else throw new RuntimeException("上传文件类型不正确！");
        return FileUploadUtil.formatDate() + file_ture_name;
    }
}
