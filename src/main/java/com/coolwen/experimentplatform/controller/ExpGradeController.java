package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.model.DTO.ModuleGradesDto;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import com.coolwen.experimentplatform.service.TotalScoreCurrentService;
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
    public String totalscore(Model model, Session session){
//        Student student = (Student) session.getAttribute("");
        List<TotalScoreCurrent> totalScoreCurrents = totalScoreCurrentService.findeAllBystuid(1);
        model.addAttribute("totalScoreCurrents",totalScoreCurrents);
        List<ModuleGradesDto> ModuleGrades = totalScoreCurrentService.ModuleGrade(1);
        model.addAttribute("ModuleGrades",ModuleGrades);
        return "home_shiyan/grade";
    }
}
