package com.coolwen.experimentplatform.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


public interface FIleService {

    public  String upload(HttpServletRequest request, MultipartFile file);
}
