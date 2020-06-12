package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 *@Description 后台管理系统 模块信息管理
 *@Author 张健银
 *@Version 1.0
 *@Date 2020/5/31
 */


@Controller
@RequestMapping("/expmodel")
public class ExpModelController {

    @Autowired
    FIleService fIleService;
    @Autowired
    ExpModelService expModelService;
    @Autowired
    KaoheModelService kaoheModelService;
    @Autowired
    ModuleTestAnswerService moduleTestAnswerService;
    @Autowired
    ModuleTestQuestService moduleTestQuestService;
    @Autowired
    ReportService reportService;
    @Autowired
    ReportAnswerService reportAnswerService;
    @Autowired
    KaoHeModelScoreService kaoHeModelScoreService;
    @Autowired
    TotalScoreCurrentService totalScoreCurrentService;
    @Autowired
    ClazzService clazzService;


    //查询模块信息页面
    @GetMapping("/list")
    public String expModelList(Model model,@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        model.addAttribute("page",expModelService.findModelList(pageNum));
        return "shiyan/lookExpModel";
    }

    //添加模块信息页面
    @GetMapping("/addExpModel")
    public String toAdd(){
        return "shiyan/newExpModel";
    }
    //进行模块添加操作
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
        expModel.setImageurl(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/ExperimentPlatform/ExpModelImage/"+file);
        expModelService.save(expModel);
        session.setAttribute("modelId",expModel.getM_id());
        return "redirect:/expmodel/addTheory";
    }
        //进行模块删除
        @GetMapping("/deleteExpModel/{id}")
    public String delete(@PathVariable("id")int id){
        //删除考核模块以及更改学生相关成绩
        KaoheModel kaoheModel = kaoheModelService.findKaoheModelByMid(id);
        if(kaoheModel != null){
            //如果该模块是考核模块
            //根据学生条件与考核模块找到学生考核模块成绩
            List<KaoHeModelScore> kaoHeModelScores = kaoHeModelScoreService.findKaoHeModelScoreByTKaohemodleIdAndStuId(kaoheModel.getId());
            for (KaoHeModelScore k : kaoHeModelScores){
                //更新学生在删除该考核模块后的当期总评成绩表信息
                TotalScoreCurrent totalScoreCurrent = totalScoreCurrentService.findTotalScoreCurrentByStuId(k.getStuId());
                totalScoreCurrent.setKaoheNum(totalScoreCurrent.getKaoheNum()-1);
                totalScoreCurrent.setmTotalScore(totalScoreCurrent.getmTotalScore()-k.getmScore());
                totalScoreCurrent.setTotalScore(totalScoreCurrent.getmTotalScore()*kaoheModel.getKaohe_baifenbi()+totalScoreCurrent.getTestScore()* kaoheModel.getTest_baifenbi());
                totalScoreCurrentService.add(totalScoreCurrent);
            }
            //删除该考核模块与模块信息
            kaoHeModelScoreService.deleteAllKaohe(kaoHeModelScores);
            kaoheModelService.deleteKaoHeModuleByMid(kaoheModel);
        }
        //删除所属的问题以及答案以及相关回复
        List<ModuleTestQuest> moduleTestQuests = expModelService.findModuleTestQuestByMId(id);
        for(ModuleTestQuest m:moduleTestQuests){
            List<ModuleTestAnswer> moduleTestAnswers = moduleTestAnswerService.findAllByQuestId(m.getQuestId());
            moduleTestAnswerService.deleteAllAnswer(moduleTestAnswers);
            expModelService.deleteModuleTestAnswerStuByQuestId(m.getQuestId());
        }
        moduleTestQuestService.deleteAllModuleTestQuest(moduleTestQuests);
        //删除实验模块报告与实验报告回答
        List<Report> reports = reportService.findReportByMId(id);
        for(Report r : reports){
            reportAnswerService.deleteReportAnswerByReportId(r.getReportId());
        }
        reportService.deleteReports(reports);
        expModelService.deleteExpModelById(id);
        return "redirect:/expmodel/list";
    }
    //进入模块更新
    @GetMapping("/updateExpModel/{id}")
    public String toUpdate(@PathVariable("id") int id,Model model,HttpServletRequest request,HttpSession session){
        ExpModel expModel = expModelService.findExpModelByID(id);
        model.addAttribute("preExpModel",expModel);
        model.addAttribute("image",expModel.getImageurl());
        session.setAttribute("modelId",expModel.getM_id());
        return "shiyan/changeExpModel";
    }
    //进行模块更新
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
            preExpModel.setImageurl(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/ExperimentPlatform/ExpModelImage/"+path);
        }
        expModelService.save(preExpModel);
        return "redirect:/expmodel/list";
    }
    //进入理论添加
    @GetMapping("/addTheory")
    public String toAddTheory(HttpSession session,Model model)
    {
        model.addAttribute("id",session.getAttribute("modelId"));
        return"shiyan/newTheory";
    }
    //执行理论添加操作
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
    //理论资料添加上传接口
    @PostMapping("/addTheoryFile/{id}")
    @ResponseBody
    public String AddExpTheory(@PathVariable("id") int id,
                               MultipartFile file,
                               HttpServletRequest request
    )
    {
        String pathString = null;
        if(file!=null) {
            String filename = file.getOriginalFilename();
            int unixSep = filename.lastIndexOf('/');
            int winSep = filename.lastIndexOf('\\');
            int pos = (winSep > unixSep ? winSep:unixSep);
            if (pos != -1)
                filename = filename.substring(pos + 1);
                pathString =  fIleService.upload(request,file,filename);
        }
        return "{\"code\":0, \"msg\":\"success\", \"fileUrl\":\"" + pathString + "\"}";
    }
    //保存理论资料路径
    @PostMapping("/savePath/{mid}")
    @ResponseBody
    public String savePathn(@PathVariable("mid") int id, @RequestParam("path") String path, HttpServletRequest request){
        ExpModel expModel = expModelService.findExpModelByID(id);
        String total = "";
        String[] url = path.split(",");
        for (String s : url){
            total += request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/ExperimentPlatform/ExpData/"+s+",";
        }
        expModel.setM_edataurl(total);
        expModelService.save(expModel);
        return "";
    }



    //理论资料修改上传接口
    @PostMapping("/updateTheoryFile/{id}")
    @ResponseBody
    public String updateExpTheory(@PathVariable("id") int id,
                               MultipartFile file,
                               HttpServletRequest request
    )
    {
        ExpModel expModel = expModelService.findExpModelByID(id);
        expModel.setM_edataurl("");
        String pathString = null;
        if(file!=null) {
            String filename = file.getOriginalFilename();
            int unixSep = filename.lastIndexOf('/');
            int winSep = filename.lastIndexOf('\\');
            int pos = (winSep > unixSep ? winSep:unixSep);
            if (pos != -1)
                filename = filename.substring(pos + 1);
            pathString =  fIleService.upload(request,file);
        }
        return "{\"code\":0, \"msg\":\"success\", \"fileUrl\":\"" + pathString + "\"}";
    }

    //进入理论更新
    @GetMapping("/updateExpTheory/{id}")
    public String toUpdateExpTheory(@PathVariable("id") int id,Model model){
        ExpModel expModel = expModelService.findExpModelByID(id);
        model.addAttribute("preExpModel",expModel);
        if(expModel.getM_edataurl() != null){
            String[] path = expModel.getM_edataurl().split(",");
            model.addAttribute("path",path);
        }
        return "shiyan/changeTheory";
    }

    //执行理论更新操作
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

    //搜索模块
    @GetMapping("/viewExpModel")
    public String viewModel(@RequestParam("m_name") String m_name,Model model){
        List<ExpModel> list = expModelService.findExpModelsBym_name(m_name);
        if(!list.isEmpty() && list != null){
            model.addAttribute("list",list);
            return "shiyan/viewExpModel";
        }

        return "redirect:/expmodel/list";
    }


    //实验大厅所有模块信息
    @GetMapping("/alltestModel")
    public String alltest(Model model,@RequestParam(value = "pageNum",required = true,defaultValue = "0")int pageNum,HttpSession session){
        Page<ExpModel> page = expModelService.finExpAll(pageNum);
        model.addAttribute("list",expModelService.finExpAll(pageNum));
        session.setAttribute("modulePageNum",pageNum);
        session.setAttribute("isAllModule",true);
        return "home_shiyan/all-test";
//        return "";
    }
    //实验大厅考核模块
    @GetMapping("/kaoheModel")
    public String kaoModelById(Model model,@RequestParam(value = "pageNum",required = true,defaultValue = "0")int pageNum,HttpSession session){
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        Page<KaoHeModelStuDTO> kaohe = kaoheModelService.findKaoheModelStuDto(student.getId(),pageNum);
        model.addAttribute("k",kaohe);
        session.setAttribute("modulePageNum",pageNum);
        session.setAttribute("isAllModule",false);
        return "home_shiyan/index";
    }

    //进入理论学习页面
    @GetMapping("/theoryStudy/{id}")
    public String theoryStudey(@PathVariable("id")int id,Model model){
        ExpModel expModel = expModelService.findExpModelByID(id);
        model.addAttribute("exp",expModel);
        String path = expModel.getM_edataurl();
        if(path != null){
            String[] paths = path.split(",");
            model.addAttribute("path",paths);
        }
        return "home_shiyan/study_update";
    }
    //模块测试和实验报告汇总页面
    @GetMapping("/moduleList")
    public String moduleList(Model model,HttpSession session,
                             @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        model.addAttribute("page1",expModelService.findModelList(pageNum));

        session.removeAttribute("msg2020612");

        return "shiyan/lookTestAndReport";
    }

    //精准返回进入模块测试或填写报告，或理论学习模块所在的页面
    @GetMapping("/moduleDispathcher")
    public String moduleDispathcher(HttpSession session, RedirectAttributes redirectAttributes){
        int pageNum = (int) session.getAttribute("modulePageNum");
        boolean flag = (boolean) session.getAttribute("isAllModule");
        redirectAttributes.addAttribute("pageNum",pageNum);
        if(flag == true){
            return "redirect:/expmodel/alltestModel";
        }else {
            return "redirect:/expmodel/kaoheModel";
        }
    }


    //首页跳转过来的模块
    @GetMapping("/home_exp/{id}")
    public String homeExp(@PathVariable("id") int id,Model model){
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        ClassModel classModel = clazzService.findById(student.getClassId());
        if(classModel != null){
            if(classModel.getClassIscurrent() == false){
                //具备考核资格并且为当期
                if(kaoheModelService.findKaoheModelByMid(id) != null){
                    //考核模块
                    KaoHeModelStuDTO kaoHeModelStuDTO = kaoheModelService.findKaoHeModelStuDTOByStuId(student.getId(),id);
                    model.addAttribute("k",kaoHeModelStuDTO);
                    return "home_shiyan/kaohe_copy";
                }else {
                    //普通实验
                    ExpModel expModel = expModelService.findExpModelByID(id);
                    model.addAttribute("exp",expModel);
                    return "home_shiyan/all-test_copy";
                }
            }else {
                //具备考核资格但为往期
                ExpModel expModel = expModelService.findExpModelByID(id);
                model.addAttribute("exp",expModel);
                return "home_shiyan/all-test_copy";
            }
        }else {
            //不具备考核资格
            ExpModel expModel = expModelService.findExpModelByID(id);
            model.addAttribute("exp",expModel);
            return "home_shiyan/all-test_copy";
        }
    }

    //继续学习
    @GetMapping("/contiuneStudy")
    public String contiunrStudy(){
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        ClassModel classModel = clazzService.findById(student.getClassId());
        if(classModel != null){
            if(classModel.getClassIscurrent() == false){
                return "redirect:/expmodel/kaoheModel";
            }else {
                return "redirect:/expmodel/alltestModel";
            }
        }else {
            return "redirect:/expmodel/alltestModel";

        }
    }



}
