package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.DTO.QuestListAnswerDto;
import com.coolwen.experimentplatform.service.ModuleTestQuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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

    //返回考试题目
    @RequestMapping("toExamPageList")
    public String toExamPage(Integer mid, Model model, HttpSession session) {
//        type1 表示的是单选题,
//        mid表示模型
        List<QuestListAnswerDto> questionsList = moduleTestQuestService.listQuestAnswerDto("1", 1);
        System.out.println(questionsList);
        model.addAttribute("radioQuestionsList", questionsList);
        //返回多选题目
        List<QuestListAnswerDto> questionsList2 = moduleTestQuestService.listQuestAnswerDto("1", 1);
        model.addAttribute("checkboxQuestionsList", questionsList2);
        return "home_shiyan/CanKaoceshitest";
    }

    /*  计算学生考试成绩并存储到数据库
     **  将表单传输的name和value以map形式接受然后遍历它
     */
    @RequestMapping("postExam")
    public String postExam(Map<String,String> map,Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Integer taotiId = null;

        //回到成绩查看页面或者其他页面
        return null;
    }
}
