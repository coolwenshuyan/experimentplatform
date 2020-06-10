package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import com.coolwen.experimentplatform.service.KaoheModelService;
import com.coolwen.experimentplatform.service.StudentService;
import com.coolwen.experimentplatform.service.TotalScoreCurrentService;
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
    public StudentService studentService;

    @Autowired
    public KaoheModelService kaoheModelService;

    /**
     * 列出所有成绩
     * @param model 传值
     * @param pageNum 分页
     * @return 页面
     */
    @GetMapping("/list")
    public String expModelList(Model model, @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        //从数据库得到所有的总成绩
        Page<StuTotalScoreCurrentDTO> totalScore= studentService.listStuTotalScoreCurrentDTO(pageNum);
        List<KaoheModel> toGetBaiFenBi=kaoheModelService.findAll();
        float kaoheBaifenbi = 0;
        float testBaifenbi = 0;
        if (toGetBaiFenBi.size()>0){
            kaoheBaifenbi=toGetBaiFenBi.get(0).getKaohe_baifenbi();
            testBaifenbi=toGetBaiFenBi.get(0).getTest_baifenbi();
        }
        model.addAttribute("pageTotalScore",totalScore);
        model.addAttribute("kaoheBaifenbi",kaoheBaifenbi);
        model.addAttribute("testBaifenbi",testBaifenbi);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+totalScore);
        return "kaohe/all_score";
    }


}
