package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.DTO.PScoreDto;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.model.ReportAnswer;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.Element;
import java.util.*;

//import com.coolwen.experimentplatform.model.StudentTestScoreDTO;

/**
 * 学生填写报告
 * @author 王雨来
 * @version 2020/5/13 12:21
 */
@Controller
//老师评分
@RequestMapping(value = "/WriteReport")
public class writeReportController {

    @Autowired
    public StudentRepository studentRepository;

    @Autowired
    private KaoheModelRepository kaoheModelRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ReportAnswerService reportAnswerService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private KaoHeModelScoreService kaoHeModelScoreService;

    @Autowired
    private ExpModelService expModelService;

//    @GetMapping(value = "/add")
//    public String loadAllModel(Model model) {
//        List<StudentTestScoreDTO> page = studentRepository.listStudentMTestAnswerDTO();
//        model.addAttribute("TPageInfo",page);
//        return "kaohe/reportGrade_ma";
//    }
//
//    @GetMapping(value = "/list")
//    public String loadModel(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {
////        List<TreportGradeDto> page = studentRepository.ListStudentDto();
//        Page<Student> c = studentService.findAll(pageNum);
//        model.addAttribute("allStu",c);
//        List<StudentTestScoreDTO> a = studentRepository.listStudentMTestAnswerDTO();
//        long modleNum = kaoheModelRepository.count();
//        model.addAttribute("allInfo",a);
//        model.addAttribute("num",modleNum);
//        List<Integer> list = new ArrayList<Integer>();
//        model.addAttribute("TPageInfo",a);
//        return "kaohe/score_management ";
//    }stuId,mid

    /**
     * 获得此模块题目
     * @param model 传值
     * @param mid 模块id
     * @return 页面
     */
    @GetMapping(value = "/{mid}/Timu" )
    public String GiveAmark(Model model,
                            @PathVariable("mid")int mid){

        //获得所有报告（排序）
        List<Report> reports= reportService.findByMidpaixu(mid);
        model.addAttribute("TiMuList",reports);

        //获取当前登录的学生id
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        int stuId = student.getId();

        //计算当前模块学生是否答题，没有答题，则计算结果为0
        int reportAnswers = reportAnswerService.findByStuIdModelId(mid,stuId);
        //如果为空，则给表中添加空数据（添加学生id，题目id）
        if (reportAnswers == 0){
            for (int i = 0; i < reports.size(); i++) {
                ReportAnswer reportAnswer = new ReportAnswer();
                reportAnswer.setStuId(stuId);
                reportAnswer.setReportId(reports.get(i).getReportId());
                reportAnswerService.addReportAnswer(reportAnswer);
            }
        }
        //查出学生回答的答案
        List<ReportAnswer> reportAnswers1 = reportAnswerService.findByStuId(stuId);
        model.addAttribute("DaAnList",reportAnswers1);

        //判断是否是考核模块，是考核模块则查询考核模块得分
        Boolean isNeedKaohe = expModelService.findExpModelByID(mid).isNeedKaohe();
        model.addAttribute("isNeedKaohe",isNeedKaohe);
        if (isNeedKaohe){
            KaoHeModelScore kaoHeModelScore = kaoHeModelScoreService.findKaoheModelScoreByMid(mid,stuId);
            model.addAttribute("kaoHeModelScore",kaoHeModelScore);

        }

        return "home_shiyan/tian";
    }


    /**
     * 保存学生的报告
     * @param model
     * @param request 请求
     * @param httpSession 获得学生填写的报告
     * @param mid
     * @return
     */
    @PostMapping(value = "/{mid}/Timu" )
    public String giveamark(Model model, HttpServletRequest request,
                            HttpSession httpSession,
//                            @RequestParam(required = true, defaultValue = "1") int stuId,
                            @PathVariable("mid")int mid){
        //获取当前登录的学生id
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        int stuId=student.getId();

        try {
            KaoHeModelScore kaoHeModelScore = kaoHeModelScoreService.findKaoheModelScoreByMid(mid,stuId);
            if (kaoHeModelScore.ismReportstate()){
                return "redirect:/WriteReport/"+mid+"/Timu";
            }
        }catch (Exception e){

        }


        //得到报告题目木
        List<Report> reports= reportService.findByMidpaixu(mid);
//        model.addAttribute("TiMuList",reports);

        //获得学生的报告
        Enumeration em = request.getParameterNames();
        //保存所有请求内容
        List<String> zyy = new ArrayList<>();

        //保存所有请求名
        List<String> z = new ArrayList<>();

        //获得所有请求名
        while (em.hasMoreElements()) {
        String name = (String) em.nextElement();
        z.add(name);
        }
        //将所有请求名排序，并获取内容添加到
        Collections.sort(z);
        for(String a:z){
            String value = request.getParameter(a);
            zyy.add(value);
        }

        for (int i = 0; i <zyy.size() ; i++) {

            Report d= reports.get(i);
            //获得学生的报告
            List<ReportAnswer> b = reportAnswerService.listByReportidAndStuID(d.getReportId(),stuId);
            //如果已经有报告则更新,没有则添加
            if (b == null || b.size() ==0){
                ReportAnswer c = new ReportAnswer();
                c.setStuReportAnswer(zyy.get(i));
                c.setScore(0);
                c.setReportId(d.getReportId());
                c.setStuId(stuId);
                reportAnswerService.addReportAnswer(c);
            }else {
                ReportAnswer c = new ReportAnswer();
                c.setId(b.get(0).getId());
                c.setStuReportAnswer(zyy.get(i));
                c.setScore(0);
                c.setReportId(d.getReportId());
                c.setStuId(stuId);
                reportAnswerService.addReportAnswer(c);
            }
        }

        //获得学生的报告
//        List<ReportAnswer> reportAnswers = reportAnswerService.findByStuId(stuId);
//        model.addAttribute("DaAnList",reportAnswers);
//
//        KaoHeModelScore kaoHeModelScore = kaoHeModelScoreService.findKaoheModelScoreByMid(mid,stuId);
//        model.addAttribute("kaoHeModelScore",kaoHeModelScore);
        return "redirect:/WriteReport/"+mid+"/Timu";
    }
}
