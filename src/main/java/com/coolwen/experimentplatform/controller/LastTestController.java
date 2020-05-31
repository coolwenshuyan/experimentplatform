package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import com.coolwen.experimentplatform.service.ModuleTestAnswerService;
import com.coolwen.experimentplatform.service.ModuleTestQuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 14:45
 * 整体模块测试题的增删改查
 */
@Controller
@RequestMapping("/shiyan")
//设置模块测试题题目的数据回显
@SessionAttributes("con")
public class LastTestController {

    /**
     * 注入模块测试题、测试题的选项的service
     */
    @Autowired
    private ModuleTestQuestService questService;

    @Autowired
    private ModuleTestAnswerService answerService;

    /**
     * 添加整体模块测试题的选项，先添加题目和选项，在添加剩下的题目信息
     * @param model 绑定参数给前端thymeleaf传值
     * @param session 存储、获取一些需要的值
     * @return 返回到静态资源下的shiyan/addLastTest.html
     */
    @GetMapping("addLastAnswer")
    public String addLastAnswer(Model model, HttpSession session) {
//        从session中取到题目内容，即con
        String con = (String) session.getAttribute("con");
//        判断con是否为空，为空，则绑定新的ModuleTestAnswer和ModuleTestQuest对象到前端，即还没有开始添加
        if (con == null || con.isEmpty() || con == "") {
            model.addAttribute("addLastAnswer", new ModuleTestAnswer());
            model.addAttribute("Lastquest", new ModuleTestQuest());
        } else {
//            不为空，则通过模块测试题的题目找到整条试题的信息
            ModuleTestQuest Lastquest = questService.findByQuestDescribe(con);
//            调用answerService的方法通过问题id找到所以问题选项，存入list
            List<ModuleTestAnswer> addLastAnswer = answerService.findAllByQuestId(Lastquest.getQuestId());
//            将选项list传给前端
            model.addAttribute("addLastAnswer", addLastAnswer);

        }
//        返回到静态资源下的shiyan/addLastTest.html
        return "shiyan/addLastTest";
    }

    /**
     * 添加整体模块测试题的选项的post方法
     * @param con 整体测试题的题目
     * @param model 绑定参数给前端传值
     * @param moduleTestAnswer 问题选项对象
     * @param moduleTestQuest 问题对象
     * @param session 数据的缓存
     * @return 返回添加整体模块测试题的选项页面
     */
    @PostMapping("addLastAnswer")
    public String addLastAnswer(String con, Model model, ModuleTestAnswer moduleTestAnswer,
                                ModuleTestQuest moduleTestQuest, HttpSession session) {
//        将问题的题目存入数据库
        moduleTestQuest.setQuestDescribe(con);
//        从session中得到问题的题目
        String Stitle = (String) session.getAttribute("con");
//        如果题目为空，就是还没有开始添加
        if (Stitle == null || Stitle.isEmpty() || Stitle == "") {
//            就允许添加题目信息
            questService.addModuleTestQuest(moduleTestQuest);
        }
//        通过题目找到该条测试题的信息，并赋值给ModuleTestQuest对象
        moduleTestQuest = questService.findByQuestDescribe(con);
//        通过ModuleTestQuest对象找到问题id，并赋值给ModuleTestAnswer问题选项对象
        moduleTestAnswer.setQuestId(moduleTestQuest.getQuestId());
//        将ModuleTestAnswer对象中的数据添加到数据库
        answerService.addAnswers(moduleTestAnswer);
//        将题目传给前端
        model.addAttribute("con", con);
        return "redirect:/shiyan/addLastAnswer";
    }


    /**
     * 添加整体模块测试题的post方法
     * @param moduleTestQuest 问题对象
     * @param session 数据的缓存
     * @param questType 整体测试题的类型
     * @param questScore 整体测试题的分数
     * @param questAnswer 整体测试题的答案
     * @param questOrder 整体测试题的序号
     * @return 返回整体测试题列表
     */
    @PostMapping("addLastTest")
    public String addQuest(ModuleTestQuest moduleTestQuest, HttpSession session,
                           String questType, float questScore,
                           String questAnswer,int questOrder) {
//        在试题表添加试题信息
//        从session中取到题目内容，即con，在添加选项时先添加的题目，所以此时题目已存在
        String con = (String) session.getAttribute("con");
//        通过模块测试题的题目找到整条试题的信息
        moduleTestQuest = questService.findByQuestDescribe(con);
//        这里做更新操作，将该整体测试题的其他信息更新到ModuleTestQuest对象
        moduleTestQuest.setQuestDescribe(con);
        moduleTestQuest.setQuestType(questType);
        moduleTestQuest.setQuestScore(questScore);
        moduleTestQuest.setQuestAnswer(questAnswer);
        moduleTestQuest.setQuestOrder(questOrder);
//        模块id默认为-1，即整体测试的模块id为-1
        moduleTestQuest.setmId(-1);
//        控制台打印ModuleTestQuest对象
        System.out.println(moduleTestQuest);
//        把ModuleTestQuest对象的数据更新到数据库
        questService.addModuleTestQuest(moduleTestQuest);
        return "redirect:/shiyan/lastTestList";
    }

    /**
     * 整体模块测试题题目列表
     * @param session  数据的缓存
     * @param page 分页信息
     * @param model 绑定参数给前端传值
     * @return 返回到静态资源下的shiyan/lookLastTest.html
     */
    @RequestMapping("lastTestList")
    public String list(HttpSession session,
                       @RequestParam(value = "page", defaultValue = "0", required=true) Integer page,
                       Model model) {
//        分页数据的条数为10，即没10条数据进行分页
        Pageable pageable = PageRequest.of(page,10);
//        分页的条件是以模块id，即mid为条件分页
        Page<ModuleTestQuest> termList = questService.findByLastPage(pageable,-1);
//        清理添加模块测试题数据回显的缓存，以便下次点击添加时时干净的页面
        String con = "";
        model.addAttribute("con",con);
//        将分页信息传给前端
        model.addAttribute("termList",termList);
        return "shiyan/lookLastTest";
    }


    /**
     * 删除整体模块测试题
     * @param questId 需要删除的模块测试题的问题id
     * @return 返回整体测试题列表
     */
    @RequestMapping("deleteLastQuest/{questId}")
    public String deleteQuest(@PathVariable("questId") int questId) {
//        删除整体模块测试题的问题id
        questService.deleteQuest(questId);
        return "redirect:/shiyan/lastTestList";
    }

    /**
     * 修改整体测试题的试题信息
     * @param questId 需要修改的模块测试题的问题id
     * @param model 绑定参数给前端传值
     * @return 返回到静态资源下的shiyan/updateLastTest.html
     */
    @GetMapping("updateLastQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId, Model model) {
//        通过问题id找到这个问题并存入ModuleTestQuest对象
        ModuleTestQuest quest = questService.findQuestByQuestId(questId);
//        用model绑定找到的问题，传给前端
        model.addAttribute("UpLastQuest", quest);
//        通过问题id找到问题的选项，并存入list中，主要用来绑定数据到前端显示
        List<ModuleTestAnswer> UpAnswer = answerService.findAllByQuestId(quest.getQuestId());
//        用model绑定找到的问题选项，传给前端
        model.addAttribute("UpLastAnswer", UpAnswer);
//        用model绑定问题id，传给前端
        model.addAttribute("qid", questId);
        return "shiyan/updateLastTest";
    }

    /**
     * 修改整体测试题的试题信息的post方法
     * @param questId 需要修改的模块测试题的问题id
     * @param quest ModuleTestQuest对象
     * @param model 绑定参数给前端传值
     * @param questDescribe 题目内容
     * @return 返回整体测试题列表
     */
    @PostMapping("updateLastQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId,
                              ModuleTestQuest quest,
                              Model model, String questDescribe) {
//        通过问题id找到这个问题并存入ModuleTestQuest对象
        ModuleTestQuest quest1 = questService.findQuestByQuestId(questId);
//        先获取修改模块测试信息get方法中修改的内容，再将修改好的题目信息更新到ModuleTestQuest对象
        quest1.setQuestDescribe(quest.getQuestDescribe());
        quest1.setQuestAnswer(quest.getQuestAnswer());
        quest1.setQuestType(quest.getQuestType());
        quest1.setQuestScore(quest.getQuestScore());
//        调用questService的方法更新数据
        questService.addModuleTestQuest(quest1);
        return "redirect:/shiyan/lastTestList";
    }

    /**
     * 在修改整体模块测试题中增加选项
     * @param model 绑定参数给前端传值
     * @param questId 需要修改的模块测试题的问题id
     * @return 返回修改整体测试题的试题信息页面
     */
    @GetMapping("upLastAnswer/{questId}")
    public String upAnswer(@PathVariable("questId") int questId, Model model) {
//        通过问题id找到这个问题并存入ModuleTestQuest对象
        ModuleTestQuest quest = questService.findQuestByQuestId(questId);
//        通过问题id找到问题的选项，并存入list中，主要用来绑定数据到前端显示
        List<ModuleTestAnswer> upAnswer = answerService.findAllByQuestId(quest.getQuestId());
//        将找到并存入的list传给前端
        model.addAttribute("UpLastAnswer", upAnswer);
        return "redirect:/shiyan/updateLastQuest/" + questId;
    }

    /**
     * 在修改整体模块测试题中增加选项的post方法
     * @param questId 需要修改的模块测试题的问题id
     * @param answerDescribe 问题选项内容
     * @param answerOrder 问题选项序号
     * @return 返回修改整体测试题的试题信息页面
     */
    @PostMapping("upLastAnswer/{questId}")
    public String upAnswer(@PathVariable("questId") int questId,
                           String answerDescribe, int answerOrder) {
//        实例化一个ModuleTestAnswer对象
        ModuleTestAnswer answer = new ModuleTestAnswer();
//        添加问题选项的信息到ModuleTestAnswer对象
        answer.setAnswerDescribe(answerDescribe);
        answer.setAnswerOrder(answerOrder);
//        将参数当前问题id存到ModuleTestAnswer对象
        answer.setQuestId(questId);
//        将新增的选项存到数据库
        answerService.addAnswers(answer);
        return "redirect:/shiyan/updateLastQuest/" + questId;
    }

    /**
     * 删除添加整体模块测试题页面的选项
     * @param answerId 需要删除的模块测试题的问题id
     * @return 返回添加整体测试题息页面
     */
    @RequestMapping("deleteLastAnswer/{answerId}")
    public String deleteAnswer(@PathVariable("answerId") int answerId) {
//        调用answerService的方法删除选项id来删除选项
        answerService.deleteAnswer(answerId);
        return "redirect:/shiyan/addLastAnswer";
    }

    /**
     * 删除修改整体模块测试题页面的选项
     * @param answerId 需要删除的模块测试题的问题id
     * @return 返回添加整体测试题息页面
     */
    @RequestMapping("deleteLastUpAnswer/{answerId}")
    public String deleteUpAnswer(@PathVariable("answerId") int answerId) {
//        通过调用answerService的方法，用选项id找到问题id
        int qId = answerService.findQuestIdByAnswerId(answerId);
//        调用answerService的方法删除选项id来删除选项
        answerService.deleteAnswer(answerId);
        return "redirect:/shiyan/updateLastQuest/" + qId;
    }


}