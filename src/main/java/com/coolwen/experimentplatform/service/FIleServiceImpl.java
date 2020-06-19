package com.coolwen.experimentplatform.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FIleServiceImpl implements FIleService {
    @Value("${web.ExpModelImage-path}")
    private String expModelImage ;

    @Value("${web.ExpData-path}")
    private String expData ;

    @Override
    public String upload(HttpServletRequest request, MultipartFile file) {

        String filePath = null;
        File dir = null;
        String format = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        format = sdf.format(new Date());
        String fileType = file.getContentType();
        if(request.getRequestURI().contains("/expmodel/addExpModel")||request.getRequestURI().contains("/expmodel/updateExpModel")){
            String realPath = expModelImage;
            dir = new File(realPath + "/"+format);
        }else if(request.getRequestURI().contains("/expmodel/addTheoryFile") || request.getRequestURI().contains("/expmodel/updateTheoryFile")){
            String realPath = expData;
            dir = new File(realPath+"/"+format);
        }
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        try {
            String filename = file.getOriginalFilename();
            //浏览器不同，可能获得的filename值不一样，如果获得的是带路径的文件名则进行文件名截取
            int temp1 = filename.lastIndexOf(92);
            if(temp1>-1)
            {
                filename = filename.substring(temp1+1,filename.length());
            }

            File fileServer = new File(dir.getAbsolutePath(), filename);
            file.transferTo(fileServer);
            filePath = format + fileServer.getName() ;

        } catch (IOException e) {

        }

        return filePath;

    }

    @Override
    public String upload(HttpServletRequest request, MultipartFile file, String fileName) {
        String filePath = null;
        File dir = null;
        String format = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        format = sdf.format(new Date());
//        String fileType = file.getContentType();
        if(request.getRequestURI().contains("/expmodel/addTheoryFile")){
            String realPath = expData;
            dir = new File(realPath+"/"+format);
        }
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        try {
//            String filename = file.getOriginalFilename();

            File fileServer = new File(dir.getAbsolutePath(), fileName);

            file.transferTo(fileServer);

            filePath = format + fileServer.getName() ;
        } catch (IOException e) {

        }

        return filePath;
    }
}
