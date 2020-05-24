package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.model.DTO.ModuleGradesDto;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import com.coolwen.experimentplatform.service.TotalScoreCurrentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping(value = "/grade")
public class ExpGradeController {

    @Autowired
    TotalScoreCurrentService totalScoreCurrentService;

    @GetMapping(value = "/score")
    public String totalscore(Model model){
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
//        System.out.println(">>>>>>>>>>>>>>>>grade>>>>>...."+student);
        List<TotalScoreCurrent> totalScoreCurrents = totalScoreCurrentService.findeAllBystuid(student.getId());
        model.addAttribute("totalScoreCurrents",totalScoreCurrents);
        List<ModuleGradesDto> ModuleGrades = totalScoreCurrentService.ModuleGrade(student.getId());
        model.addAttribute("ModuleGrades",ModuleGrades);
        return "home_shiyan/grade";
    }

    @GetMapping(value = "/kaohe")
    public String kaohe(){
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        return "redirect:/expmodel/kaoheModel/"+student.getId();
    }
}
