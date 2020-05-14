package com.coolwen.experimentplatform.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.utils
 * @ClassName: FileUtil
 * @Author: Txc
 * @Description: 文件操作，包括保存和删除
 * @Date: 2020/5/14 0014 12:11
 * @Version: 1.0
 */
public class FileUtil {
    //保存文件
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        /**
         * Create by Txc at 2020/5/14 0014 12:13
         * @Method uploadFile
         * @Description
         * @param file 文件
         * @param filePath  文件保存地址
         * @param fileName  文件名
         * @Return void
         * @Exception 
         */
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    
    //删除文件
    public static boolean deleteFile(String fileName) {
        /**
         * Create by Txc at 2020/5/14 0014 12:15
         * @Method deleteFile
         * @Description
         * @param fileName 文件名
         * @Return boolean
         * @Exception 
         */
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
//生成通用唯一识别编码
    public static String renameToUUID(String fileName) {
        /**
         * Create by Txc at 2020/5/14 0014 12:24
         * @Method renameToUUID
         * @Description
         * @param fileName
         * @Return java.lang.String
         * @Exception 
         */
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
