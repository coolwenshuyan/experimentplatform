package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ExpModel;

import com.coolwen.experimentplatform.service.ExpModelService;
import com.coolwen.experimentplatform.service.FIleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        return "expmodel/list";
    }

    //模块添加
    @GetMapping("/addExpModel")
    public String toAdd(){
        return "expmodel/addExpModel";
    }

    @PostMapping("/addExpModel")
    public String Add(String m_name,
                      String m_manager,
                      String m_type,
                      int m_classhour,
                      String m_inurl,
                      MultipartFile m_image,
                      HttpServletRequest request,
                      RedirectAttributes attributes
                      )
    {
        ExpModel expModel = new ExpModel();
        String file = fIleService.upload(request,m_image);
        expModel.setM_name(m_name);
        expModel.setM_manager(m_manager);
        expModel.setM_typr(m_type);
        expModel.setClasshour(m_classhour);
        expModel.setM_inurl(m_inurl);
        expModel.setImageurl(file);
        expModelService.save(expModel);
        attributes.addAttribute("modelId",expModel.getM_id());
        return "redirect:/exmodel/addTheory";
    }
//模块删除
    @PostMapping("/deleteExpModel/{id}")
    public String delete(@PathVariable("id")int id){
        expModelService.deleteExpModelById(id);
        return "expmodel/list";
    }
//模块更新
    @GetMapping("/updateExpModel/{id}")
    public String toUpdate(@PathVariable("id") int id,Model model){
        ExpModel expModel = expModelService.findExpModelByID(id);
        model.addAttribute("preExpModel",expModel);
        return "expmodel/updateExpModel";
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
        preExpModel.setM_typr(m_type);
        preExpModel.setClasshour(m_classhour);
        preExpModel.setM_inurl(m_inurl);
        preExpModel.setImageurl(fIleService.upload(request,m_image));
        expModelService.save(preExpModel);
        return "expmodel/updateExpTheory";
    }
//理论添加
    @GetMapping("/addTheory/{modelId}")
    public String toAddTheory(@PathVariable("modelId")int modeId,Model model)
    {
        model.addAttribute("id",modeId);
        return"expmodel/addExpTheory";
    }

    @PostMapping("/addTheorry/{id}")
    public String AddExpTheory(@PathVariable("id") int id,
                               String m_introduce,
                               String m_purpose,
                               String m_principle,
                               String m_content,
                               String m_edata_intro,
                               String m_step,
                               MultipartFile[] files,
                               HttpServletRequest request
                               )
    {
        StringBuffer stringBuffer = new StringBuffer();
        if(files.length>0){
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                stringBuffer.append(fIleService.upload(request,file)+",");
            }
        }
        ExpModel expModel = expModelService.findExpModelByID(id);
        expModel.setIntroduce(m_introduce);
        expModel.setPurpose(m_purpose);
        expModel.setPrinciple(m_principle);
        expModel.setM_content(m_content);
        expModel.setM_edata_intro(m_edata_intro);
        expModel.setM_step(m_step);
        expModel.setM_edataurl(stringBuffer.toString());
        expModelService.save(expModel);
        return "redirect:/expmodel/list";
    }
//理论更新
    @GetMapping("/updateExpTheory/{id}")
    public String toUpdateExpTheory(@PathVariable("id") int id,Model model){
        ExpModel expModel = expModelService.findExpModelByID(id);
        model.addAttribute("preExpModel",expModel);
        return "expmodel/updateExpTheory";
    }


    @PostMapping("/updateExpTheory/{id}")
    public String updateExpTheory(@PathVariable("id") int id,
                               String m_introduce,
                               String m_purpose,
                               String m_principle,
                               String m_content,
                               String m_edata_intro,
                               String m_step,
                               MultipartFile[] files,
                               HttpServletRequest request
    )
    {
        StringBuffer stringBuffer = new StringBuffer();
        if(files.length>0){
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                stringBuffer.append(fIleService.upload(request,file)+",");
            }
        }
        ExpModel expModel = expModelService.findExpModelByID(id);
        expModel.setIntroduce(m_introduce);
        expModel.setPurpose(m_purpose);
        expModel.setPrinciple(m_principle);
        expModel.setM_content(m_content);
        expModel.setM_edata_intro(m_edata_intro);
        expModel.setM_step(m_step);
        expModel.setM_edataurl(stringBuffer.toString());
        expModelService.save(expModel);
        return "redirect:/expmodel/list";
    }

//理论删除
    @PostMapping("/deleteTheory/{id}")
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




}
