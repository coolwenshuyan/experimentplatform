package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.TotalScorePassRepository;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.OneModelScoreDTO;
import com.coolwen.experimentplatform.model.DTO.PassTotalScoreDTO;
import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 总成绩管理
 * 列出往期所有的成绩
 * @author 王雨来
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping(value = "/passTotalscore")
public class TotalscorePassController {

    @Autowired
    public TotalScoreCurrentService totalScoreCurrentService;

    @Autowired
    public TotalScorePassService totalScorePassService;

    @Autowired
    public StudentService studentService;

    @Autowired
    public KaoheModelService kaoheModelService;

    @Autowired
    public ClazzService clazzService;

    /**
     * 列出所有成绩
     * @param model 传值
     * @param pageNum 分页
     * @return 页面
     */
    @GetMapping("/list")
    public String expModelList(Model model, @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        Page<TotalScorePass> totalScorePasses = totalScorePassService.findAll(pageNum);
        for(TotalScorePass i:totalScorePasses){
            System.out.println(i);

        Student student = studentService.findStudentById(i.getStuId());
        ClassModel classModel = clazzService.findById(student.getId());

        List<OneModelScoreDTO> oneModelScoreDTOS;
//        PassTotalScoreDTO passTotalScoreDTO = new(student.getStuXuehao(),student.getStuName(),classModel.getClassName(),oneModelScoreDTOS,i.getmTotalScore(),oneModelScoreDTOS,i.get;);


        }
        return null;
    }


}
