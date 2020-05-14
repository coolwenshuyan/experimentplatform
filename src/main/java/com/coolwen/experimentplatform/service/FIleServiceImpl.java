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
        }else if(request.getRequestURI().contains("/expmodel/addTheoryFile")){
            String realPath = expData;
            dir = new File(realPath+"/"+format);
        }
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        try {
            String filename = file.getOriginalFilename();

            File fileServer = new File(dir.getAbsolutePath(), filename);

            file.transferTo(fileServer);

            filePath = format + fileServer.getName() ;
        } catch (IOException e) {

        }

        return filePath;

    }
}
