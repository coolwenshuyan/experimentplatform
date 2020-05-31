package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.DTO.PScoreDto;
import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.model.ReportAnswer;
import com.coolwen.experimentplatform.service.ReportAnswerService;
import com.coolwen.experimentplatform.service.ReportService;
import com.coolwen.experimentplatform.service.ScoreService;
import com.coolwen.experimentplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

//        List<PScoreDto> score = scoreService.listScorerDTOBystudentId(stuId,2);
        //获得所有报告
        List<Report> reports= reportService.findByMid(mid);
        model.addAttribute("TiMuList",reports);
        int stuId = 1;
        //测试的是id为一的学生

        //获得学生已有的答案,如未做过则为空
        List<ReportAnswer> reportAnswers = reportAnswerService.findByStuId(stuId);
        model.addAttribute("DaAnList",reportAnswers);

        System.out.println(">>>>>>>>>>>>>>>>>>"+reports);
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

        //得到报告题目木
        List<Report> reports= reportService.findByMid(mid);
        model.addAttribute("TiMuList",reports);

        //获得学生的报告
        Enumeration em = request.getParameterNames();
        List<String> zyy = new ArrayList<>();
        while (em.hasMoreElements()) {
        String name = (String) em.nextElement();
        String value = request.getParameter(name);
        System.out.println("<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>value"+value);
        zyy.add(value);
        }

        //测试的是id为一的学生
        int stuId=1;

        //获得学生的报告
        List<ReportAnswer> reportAnswers = reportAnswerService.findByStuId(stuId);
        model.addAttribute("DaAnList",reportAnswers);

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
        return "home_shiyan/tian";
    }
}
