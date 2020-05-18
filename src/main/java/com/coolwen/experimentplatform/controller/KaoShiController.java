package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.QuestListAnswerDto;
import com.coolwen.experimentplatform.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @author CoolWen
 * @version 2020-05-17 17:57
 */

@Controller
@RequestMapping("/kaoshi")
public class KaoShiController {

    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ModuleTestQuestService moduleTestQuestService;

    @Autowired
    private ModuleTestAnswerStuService moduleTestAnswerStuService;

    @Autowired
    private ModuleTestAnswerService moduleTestAnswerService;

    @Autowired
    private KaoHeModelScoreService kaoHeModelScoreService;

    @Autowired
    private KaoheModelService kaoheModelService;

    @Autowired
    private TotalScoreCurrentService totalScoreCurrentService;

    //返回考试题目
    @RequestMapping("/{mid}/toExamPageList")
    public String toExamPage(@PathVariable("mid") Integer mid,
                             Model model, HttpSession session) {
//        type1 表示的是单选题,
//        mid表示模型
        List<QuestListAnswerDto> questionsList = moduleTestQuestService.listQuestAnswerDto("单选", mid);
        System.out.println("questionsList1:>>>>>>>>>>>>>"+questionsList);
        model.addAttribute("radioQuestionsList", questionsList);
        model.addAttribute("midd", mid);
        //返回多选题目
        List<QuestListAnswerDto> questionsList2 = moduleTestQuestService.listQuestAnswerDto("多选", mid);
        model.addAttribute("checkboxQuestionsList", questionsList2);
        System.out.println("questionsList2:>>>>>>>>>>>>>"+questionsList2);
        return "home_shiyan/CanKaoceshitest";
    }




    /*  计算学生考试成绩并存储到数据库
     **  将表单传输的name和value以map形式接受然后遍历它
     */
    @RequestMapping("/{mid}/postExam")
    public String postExam(Map<String,String> map,
                           @PathVariable("mid") Integer mid,
                           Model model, HttpSession session, HttpServletRequest request ) {
        String username = (String) session.getAttribute("username");
        Integer taotiId = null;

        int stuId = 1;

        Enumeration em = request.getParameterNames();
        float fs = 0;
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            System.out.println("name<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+name);
            String[] value = request.getParameterValues(name);

            List<ModuleTestAnswerStu> pd = moduleTestAnswerStuService.findAllModuleTestAnswerStuByStuidAndQuestId(stuId, Integer.parseInt(name));

            ModuleTestAnswerStu moduleTestAnswerStu = new ModuleTestAnswerStu();

            if (pd.size()>0){
                Integer zgdxdid = pd.get(0).getId();
                moduleTestAnswerStu.setId(zgdxdid);
            }

            moduleTestAnswerStu.setStu_id(stuId);
            moduleTestAnswerStu.setQuest_id(Integer.parseInt(name));
            String daAn = "";
            for(String c : value){
                ModuleTestAnswer moduleTestAnswer = moduleTestAnswerService.findByAnswerId(Integer.parseInt(c));
                daAn+=moduleTestAnswer.getAnswerOrder();
            }
            moduleTestAnswerStu.setStu_quest_answer(daAn);

            ModuleTestQuest moduleTestQuest = moduleTestQuestService.findQuestByQuestId(Integer.parseInt(name));
            String rightDaAn = moduleTestQuest.getQuestAnswer();

            if (daAn.equals(rightDaAn)){
                Integer fs1 = (int) moduleTestQuest.getQuestScore();
                moduleTestAnswerStu.setScore(fs1);
                fs+=fs1;
            }

            moduleTestAnswerStuService.add(moduleTestAnswerStu);
        }

        KaoheModel kh = kaoheModelService.findKaoheModelByMid(mid);

        KaoHeModelScore khs = kaoHeModelScoreService.findKaoheModelScoreByMid(mid ,stuId);
        System.out.println("dddddddddddd"+khs+","+khs.getId());
        khs.setmTestScore(fs);

        float ms = (khs.getmReportScore()*kh.getM_report_baifenbi()+fs*kh.getM_test_baifenbi())*khs.getmScale();
        khs.setmScore(ms);
        khs.setmTeststate(true);

        kaoHeModelScoreService.update(khs);

        TotalScoreCurrent tsc = totalScoreCurrentService.findTotalScoreCurrentByStuID(stuId);

        tsc.setmTotalScore(tsc.getmTotalScore()+ms);
        tsc.setTotalScore(tsc.getmTotalScore()*kh.getKaohe_baifenbi()+tsc.getTestScore()*kh.getTest_baifenbi());

        totalScoreCurrentService.update(tsc);
        //回到成绩查看页面或者其他页面
        return "home_shiyan/CanKaoceshitest";
    }
}
