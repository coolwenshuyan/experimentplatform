package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ExpModel;

import com.coolwen.experimentplatform.service.ExpModelService;
import com.coolwen.experimentplatform.service.FIleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/expmodel")
public class ExpModelController {

    @Autowired
    FIleService fIleService;
    @Autowired
    ExpModelService expModelService;


//模块信息页面
    @GetMapping("/list")
    public String expModelList(Model model,@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        model.addAttribute("page",expModelService.findModelList(pageNum));
        return "shiyan/lookExpModel";
    }

    //模块添加
    @GetMapping("/addExpModel")
    public String toAdd(){
        return "shiyan/newExpModel";
    }

    @PostMapping("/addExpModel")
    public String Add(String m_name,
                      String m_manager,
                      String m_type,
                      int m_classhour,
                      String m_inurl,
                      MultipartFile m_image,
                      HttpServletRequest request,
                      HttpSession session
                      )
    {
        ExpModel expModel = new ExpModel();
        String file = fIleService.upload(request,m_image);
        expModel.setM_name(m_name);
        expModel.setM_manager(m_manager);
        expModel.setM_type(m_type);
        expModel.setClasshour(m_classhour);
        expModel.setM_inurl(m_inurl);
        expModel.setImageurl(file);
        expModelService.save(expModel);
        session.setAttribute("modelId",expModel.getM_id());
        return "redirect:/expmodel/addTheory";
    }
//模块删除
    @GetMapping("/deleteExpModel/{id}")
    public String delete(@PathVariable("id")int id){
        expModelService.deleteExpModelById(id);
        return "redirect:/expmodel/list";
    }
//模块更新
    @GetMapping("/updateExpModel/{id}")
    public String toUpdate(@PathVariable("id") int id,Model model,HttpServletRequest request){
        ExpModel expModel = expModelService.findExpModelByID(id);
        model.addAttribute("preExpModel",expModel);
        model.addAttribute("image",request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/ExperimentPlatform/ExpModelImage/"+expModel.getImageurl());
        return "shiyan/changeExpModel";
    }

    @PostMapping("/updateExpModel/{id}")
    public String updateExpModedl(String m_name,
                                  String m_manager,
                                  String m_type,
                                  int m_classhour,
                                  String m_inurl,
                                  MultipartFile m_image,
                                  HttpServletRequest request,
                                  @PathVariable("id") int id
                                  )
    {
        ExpModel preExpModel = expModelService.findExpModelByID(id);
        preExpModel.setM_name(m_name);
        preExpModel.setM_manager(m_manager);
        preExpModel.setM_type(m_type);
        preExpModel.setClasshour(m_classhour);
        preExpModel.setM_inurl(m_inurl);
        String path = fIleService.upload(request,m_image);
        if(path != null){
            preExpModel.setImageurl(path);
        }
        expModelService.save(preExpModel);
        return "redirect:/expmodel/list";
    }
//理论添加
    @GetMapping("/addTheory")
    public String toAddTheory(HttpSession session,Model model)
    {
        model.addAttribute("id",session.getAttribute("modelId"));
        return"shiyan/newTheory";
    }

    @PostMapping("/addTheory/{id}")
    public String AddExpTheory(@PathVariable("id") int id,
                               String m_introduce,
                               String m_purpose,
                               String m_principle,
                               String m_content,
                               String m_edata_intro,
                               String m_step
                               )
    {
        ExpModel expModel = expModelService.findExpModelByID(id);
        expModel.setIntroduce(m_introduce);
        expModel.setPurpose(m_purpose);
        expModel.setPrinciple(m_principle);
        expModel.setM_content(m_content);
        expModel.setM_edata_intro(m_edata_intro);
        expModel.setM_step(m_step);
        expModelService.save(expModel);
        return "redirect:/expmodel/list";
    }
//理论资料上传接口
    @PostMapping("/addTheoryFile/{id}")
    @ResponseBody
    public String AddExpTheory(@PathVariable("id") int id,
                               MultipartFile file,
                               HttpServletRequest request
    )
    {
        System.out.println(file);
        String pathString = null;
        if(file!=null) {
            //获取上传的文件名称
            String filename = file.getOriginalFilename();
            //文件上传时，chrome与IE/Edge对于originalFilename处理方式不同
            //chrome会获取到该文件的直接文件名称，IE/Edge会获取到文件上传时完整路径/文件名
            //Check for Unix-style path
            int unixSep = filename.lastIndexOf('/');
            //Check for Windows-style path
            int winSep = filename.lastIndexOf('\\');
            //cut off at latest possible point
            int pos = (winSep > unixSep ? winSep:unixSep);
            if (pos != -1)
                filename = filename.substring(pos + 1);
            pathString =  fIleService.upload(request,file)+",";
        }
        ExpModel expModel = expModelService.findExpModelByID(id);
        expModel.setM_edataurl(pathString);
        expModelService.save(expModel);
        return "{\"code\":0, \"msg\":\"success\", \"fileUrl\":\"" + pathString + "\"}";
    }


    //理论更新
    @GetMapping("/updateExpTheory/{id}")
    public String toUpdateExpTheory(@PathVariable("id") int id,Model model){
        ExpModel expModel = expModelService.findExpModelByID(id);
        model.addAttribute("preExpModel",expModel);
        return "shiyan/changeTheory";
    }


    @PostMapping("/updateExpTheory/{id}")
    public String updateExpTheory(@PathVariable("id") int id,
                               String m_introduce,
                               String m_purpose,
                               String m_principle,
                               String m_content,
                               String m_edata_intro,
                               String m_step
    )
    {

        ExpModel expModel = expModelService.findExpModelByID(id);
        expModel.setIntroduce(m_introduce);
        expModel.setPurpose(m_purpose);
        expModel.setPrinciple(m_principle);
        expModel.setM_content(m_content);
        expModel.setM_edata_intro(m_edata_intro);
        expModel.setM_step(m_step);
        expModelService.save(expModel);
        return "redirect:/expmodel/list";
    }

//理论删除
    @GetMapping("/deleteTheory/{id}")
    public String deleteTheory(@PathVariable("id")int id){
        ExpModel expModel = expModelService.findExpModelByID(id);
        expModel.setIntroduce(null);
        expModel.setPurpose(null);
        expModel.setPrinciple(null);
        expModel.setM_content(null);
        expModel.setM_edata_intro(null);
        expModel.setM_step(null);
        expModel.setM_edataurl(null);
        expModelService.save(expModel);
        return "redirect:/expmodel/list";
    }

    @GetMapping("/viewExpModel")
    public String viewModel(@RequestParam("m_name") String m_name,Model model){
        List<ExpModel> list = expModelService.findExpModelsBym_name(m_name);
        if(!list.isEmpty() && list != null){
            model.addAttribute("list",list);
            return "shiyan/viewExpModel";
        }

        return "redirect:/expmodel/list";
    }




}
