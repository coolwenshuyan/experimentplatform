package com.coolwen.experimentplatform.controller;

import antlr.ASTNULLType;
import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.PScoreDto;
import com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO;
//import com.coolwen.experimentplatform.model.StudentTestScoreDTO;
import com.coolwen.experimentplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.DTD;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *@Description 后台管理系统 教师评分
 *@Author 欧天
 *@Version 1.0
 *@Date 2020/5/31
 */


@Controller
//老师评分
@RequestMapping(value = "/TreportGrade")
public class TreportGradeController {

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
    private KaoHeModelScoreService kaoHeModelScoreService;

    @Autowired
    private KaoheModelService kaoheModelService;

    @Autowired
    private TotalScoreCurrentService totalScoreCurrentService;

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

    //进入教师评分系统
    @GetMapping(value = "/{stuId}/{mid}/giveMark" )
    public String GiveAmark(Model model,
                            @PathVariable("stuId") int stuId,
                            @PathVariable("mid")int mid
    ) {
        List<PScoreDto> score = scoreService.listScorerDTOBystudentId(stuId,mid);
        model.addAttribute("zjy",score);
        System.out.println(">>>>>>>>>>>>>>>>>>"+score);
        return "kaohe/reportGrade_ma";
    }



    //教师评分
    @PostMapping(value = "/{stuId}/{mid}/giveMark" )
    public String giveamark(Model model, HttpServletRequest request,
                            @PathVariable("stuId") int stuId,
                            @PathVariable("mid")int mid

    ) {
        List<PScoreDto> score = scoreService.listScorerDTOBystudentId(stuId,mid);
        model.addAttribute("zjy",score);
        System.out.println(">>>>>>>>>>>>>>>>>>"+score);
        Enumeration em = request.getParameterNames();
        List<String> zyy = new ArrayList<>();

        while (em.hasMoreElements()) {
        String name = (String) em.nextElement();
        String value = request.getParameter(name);
        System.out.println("<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+value);
        zyy.add(value);
        }

        float fs = 0;
        //判断题目数量然后进行打分
        for (int i = 0; i <zyy.size() ; i++) {
            PScoreDto d= score.get(i);
            ReportAnswer c = reportAnswerService.findByReportByreportid(d.getReportid());
            Integer a = Integer.parseInt(zyy.get(i));
            fs+=a;
            c.setScore(a);
            reportAnswerService.updateOne(c);
        }


        KaoheModel kh = kaoheModelService.findKaoheModelByMid(mid);

        KaoHeModelScore khs = kaoHeModelScoreService.findKaoheModelScoreByMid(mid ,stuId);
        System.out.println("dddddddddddd"+khs);
        khs.setmReportScore(fs);

        float ms = (fs*kh.getM_report_baifenbi()+khs.getmTestScore()*kh.getM_test_baifenbi())*khs.getmScale();
        khs.setmScore(ms);
        khs.setmReportstate(true);

        kaoHeModelScoreService.update(khs);

        TotalScoreCurrent tsc = totalScoreCurrentService.findTotalScoreCurrentByStuID(stuId);

        tsc.setmTotalScore(tsc.getmTotalScore()+ms);
        tsc.setTotalScore(tsc.getmTotalScore()*kh.getKaohe_baifenbi()+tsc.getTestScore()*kh.getTest_baifenbi());

        totalScoreCurrentService.update(tsc);

        return "kaohe/reportGrade_ma";
    }
}
