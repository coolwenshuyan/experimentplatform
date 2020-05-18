package com.coolwen.experimentplatform.controller;

import antlr.ASTNULLType;
import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.PScoreDto;
import com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO;
//import com.coolwen.experimentplatform.model.StudentTestScoreDTO;
import com.coolwen.experimentplatform.service.ReportAnswerService;
import com.coolwen.experimentplatform.service.ScoreService;
import com.coolwen.experimentplatform.service.StudentService;
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

    @GetMapping(value = "/{stuId}/{mid}/giveMark" )
    public String GiveAmark(Model model,
                            @RequestParam(required = true, defaultValue = "1") int stuId,
                            @RequestParam(required = true, defaultValue = "1") int mid
    ) {
        List<PScoreDto> score = scoreService.listScorerDTOBystudentId(stuId,2);
        model.addAttribute("zjy",score);
        System.out.println(">>>>>>>>>>>>>>>>>>"+score);
        return "kaohe/reportGrade_ma";
    }



    @PostMapping(value = "/{stuId}/{mid}/giveMark" )
    public String giveamark(Model model, HttpServletRequest request,
                            @RequestParam(required = true, defaultValue = "1") int stuId,
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
        for (int i = 0; i <zyy.size() ; i++) {
            PScoreDto d= score.get(i);
            ReportAnswer c = reportAnswerService.findByReportByreportid(d.getReportid());
            c.setScore(Integer.parseInt(zyy.get(i)));
            reportAnswerService.updateOne(c);
        }
        return "kaohe/reportGrade_ma";
    }
}
