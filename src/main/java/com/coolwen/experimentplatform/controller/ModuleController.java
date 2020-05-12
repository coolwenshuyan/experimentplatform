package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import com.coolwen.experimentplatform.service.ModuleTestAnswerService;
import com.coolwen.experimentplatform.service.ModuleTestQuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.Attribute;
import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 14:45
 */
@Controller
//@ResponseBody
public class ModuleController {

    @Autowired
    private ModuleTestQuestService questService;

    @Autowired
    private ModuleTestAnswerService answerService;

    /**
     * 添加实验模块试题
     * @return
     */
    @GetMapping("/addQuest")
    public String addQuest(){
        return "/addQuest";
    }

    @PostMapping("/addQuest")
    public String addQuest(String questDescribe,String questType,String questAnswer,float questScore,int questOrder,int mId){
//        在试题表添加试题信息
        ModuleTestQuest quest = new ModuleTestQuest();
        quest.setQuestDescribe(questDescribe);
        quest.setQuestType(questType);
        quest.setQuestAnswer(questAnswer);
        quest.setQuestScore(questScore);
        quest.setQuestOrder(questOrder);
        quest.setmId(mId);
        questService.addModuleTestQuest(quest);

        return "/questList";
    }

    /**
     * 修改试题信息
     * @param questId
     * @param model
     * @return
     */
    @GetMapping("/updateQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId ,Model model){
        ModuleTestQuest quest = questService.update(questId);
//        model.addAttribute("quest",quest);
        return "/updateQuest";
    }

    @PostMapping("/updateQuest/{questId}")
    public String update(@PathVariable("questId") int questId,ModuleTestQuest quest){
        ModuleTestQuest q = questService.update(questId);

        q.setQuestDescribe(quest.getQuestDescribe());
        q.setQuestType(quest.getQuestType());
        q.setQuestAnswer(quest.getQuestAnswer());
        q.setQuestScore(quest.getQuestScore());
        q.setQuestOrder(quest.getQuestOrder());
        q.setmId(quest.getmId());
        questService.addModuleTestQuest(q);
        return "redirect:/questList";
    }


//删除试题
    @RequestMapping("/deleteQuest/{questId}")
    public String deleteQuest(@PathVariable("questId")int questId){
        questService.deleteQuest(questId);
        return "/questList";
    }

//    以题目模糊查询
    @RequestMapping("/loadQuest")
    public List<ModuleTestQuest> loadQuest(String questDescribe){
        return questService.load(questDescribe);
    }

//    试题列表，以实验模块id（mid）、试题序号（questOrder）排序
    @RequestMapping("/loadAll")
    public List<ModuleTestQuest> loadAll(){
        return questService.loadAll();
    }

//    在答案表里面添加选项
    @PostMapping("/addAnswer")
    public String addAnswer(String answerDescribe) {
        ModuleTestAnswer answer = new ModuleTestAnswer();
        answer.setAnswerDescribe(answerDescribe);
        answerService.addAnswerDescribe(answer);
        return "/answerList";
    }

}
