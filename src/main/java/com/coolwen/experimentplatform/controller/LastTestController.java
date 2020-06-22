package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestAnswerStu;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import com.coolwen.experimentplatform.service.ModuleTestAnswerService;
import com.coolwen.experimentplatform.service.ModuleTestAnswerStuService;
import com.coolwen.experimentplatform.service.ModuleTestQuestService;
import com.coolwen.experimentplatform.service.ScoreUpdateService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 14:45
 * 整体模块测试题的增删改查
 */
@Controller
@RequestMapping("/shiyan")
//设置模块测试题题目的数据回显
@SessionAttributes(value = {"questDescribe", "questType", "questScore", "questAnswer", "questOrder"})
public class LastTestController {

    /**
     * 注入模块测试题、测试题的选项的service
     */
    @Autowired
    private ModuleTestQuestService questService;

    @Autowired
    private ModuleTestAnswerService answerService;

    @Autowired
    private ScoreUpdateService scoreUpdateService;

    @Autowired
    private ModuleTestAnswerStuService moduleTestAnswerStuService;


    @GetMapping("addLastQuest")
    public String addQuest(Model model, HttpSession session) {

//        从缓存中取到questDescribe，即题目的信息
        String questDescribe = (String) session.getAttribute("questDescribe");
        System.out.println("打印题目信息~~~~~~" + questDescribe);

        int mId = -1;
//        开始拦截，即学生已作答的模块不允许添加试题
//        找到当前模块的所有试题
        List<ModuleTestQuest> questList = questService.find(mId);
        List<ModuleTestAnswerStu> stuList = new ArrayList();
//        遍历试题
        for (ModuleTestQuest q : questList) {
//            找到对应问题的学生答题记录
            stuList = moduleTestAnswerStuService.findByQuest_id(q.getQuestId());
//            如果记录不为空，stuList列表
//            if (stu != null) {
//                stuList.add(stu);
////                System.out.println("————————————stuList" + stuList);
//            }
        }

//        如果stuList列表为空

        if (stuList == null || stuList.isEmpty() || CollectionUtils.isEmpty(stuList)) {
//            判断题目是否为空，如果为空就允许添加对象
            if (questDescribe == null || questDescribe.isEmpty() || questDescribe == "") {
                model.addAttribute("addLastAnswer", new ModuleTestAnswer());
                model.addAttribute("Lastquest", new ModuleTestQuest());

            } else {
//                如果题目不为空，先从session中取到在添加试题的post方法里存入的questId，即问题id
                int qId = (int) session.getAttribute("questId");
//                定义一个quest对象，并以当前缓存的questId查找这个题目的所有信息赋值给quest
                ModuleTestQuest quest = questService.findQuestByQuestId(qId);
//                再用那个questId查找对应题目的选项，并存入ModuleTestAnswer的list里面
                List<ModuleTestAnswer> addAnswer = answerService.findAllByQuestId(qId);
//                传递查出来的参数，将数据和前端绑定
                model.addAttribute("addLastAnswer", addAnswer);
                model.addAttribute("Lastquest", quest);
//                清除提示缓存
                session.removeAttribute("errorInformation");
                session.removeAttribute("badAnswer");
            }
            model.addAttribute("mId", mId);
//            返回到静态资源下的shiyan/addTest.html
            return "shiyan/addLastTest";
        } else {
//            如果列表不为空，就返回信息
            System.out.println("不允许作答————————");
            String message = "学生已作答，不允许添加试题";
            session.setAttribute("msg2020612", message);
            return "redirect:/shiyan/lastTestList";
        }
    }


    /**
     * 添加整体模块测试题的post方法
     *
     * @param moduleTestQuest 问题对象
     * @param session         数据的缓存
     * @param questType       整体测试题的类型
     * @param questScore      整体测试题的分数
     * @param questAnswer     整体测试题的答案
     * @param questOrder      整体测试题的序号
     * @return 返回整体测试题列表
     */
    @PostMapping("addLastQuest")
    public String addQuest(ModuleTestQuest moduleTestQuest, HttpSession session, Model model,
                           String questDescribe,
                           String questType, float questScore,
                           String questAnswer, int questOrder) {
        System.out.println("开始添加++++++——————————");
//        在试题表添加试题信息
//        通过模块测试题的题目找到整条试题的信息
        moduleTestQuest.setQuestDescribe(questDescribe);
        moduleTestQuest.setQuestType(questType);
        moduleTestQuest.setQuestScore(questScore);
        moduleTestQuest.setQuestAnswer(questAnswer);
        moduleTestQuest.setQuestOrder(questOrder);
//        模块id默认为-1，即整体测试的模块id为-1
        moduleTestQuest.setmId(-1);
//        控制台打印ModuleTestQuest对象
        System.out.println(moduleTestQuest);

//        多选题答案验证
        String dana = moduleTestQuest.getQuestAnswer();
        String[] daanList = dana.split(",");
        for (String a : daanList) {
            System.out.println(a);
            try {
                Integer.parseInt(a);

            }catch (Exception e){
                session.setAttribute("badAnswer","输入的答案包含非法字符");
                return "redirect:/shiyan/addQuest";
            }
        }
//        选择题目类型和答案对比
        String a = moduleTestQuest.getQuestType();
        String b = moduleTestQuest.getQuestAnswer();
        if (a.equals("单选")){
            try {
                Integer.parseInt(b);
                questService.addModuleTestQuest(moduleTestQuest);
            }
            catch (Exception e)
            {
                session.setAttribute("errorInformation","单选答案必须是数字");
                return "redirect:/shiyan/addLastQuest";
            }
        }else{

//            利用questService里的保存方法，将数据存到数据库
            questService.addModuleTestQuest(moduleTestQuest);
        }

//        将这个问题id存入session
        session.setAttribute("questId", moduleTestQuest.getQuestId());

//        利用model绑定数据到前端，实现数据回显
        model.addAttribute("questDescribe", questDescribe);
        model.addAttribute("questType", questType);
        model.addAttribute("questScore", questScore);
        model.addAttribute("questAnswer", questAnswer);
        model.addAttribute("questOrder", questOrder);

        return "redirect:/shiyan/addLastQuest";
    }

    /**
     * 在添加模块测试题中添加选项
     *
     * @return 返回静态资源下的shiyan/addAnswer.html
     */
    @GetMapping("addLastAnswer")
    public String addAnswer() {
//       返回静态资源下的shiyan/addLastAnswer.html
        return "shiyan/addLastAnswer";
    }

    /**
     * 在添加模块测试题中添加选项的post方法
     *
     * @param moduleTestAnswer 模块测试题的选项对象
     * @param session          数据的缓存空间
     * @return 返回添加模块测试题页面
     */
    @PostMapping("addLastAnswer")
    public String addAnswer(ModuleTestAnswer moduleTestAnswer, HttpSession session) {
//        从添加模块测试题post方法中存入的问题id取出来，并赋值给qId
        int qId = (int) session.getAttribute("questId");
//        控制台打印获取的问题id
        System.out.println("qId:-------" + qId);
//        将问题id存入moduleTestAnswer对象，以便每次添加选项的问题id都是该问题的问题id
        moduleTestAnswer.setQuestId(qId);
//        将添加的ModuleTestAnswer数据存入数据库
        answerService.addAnswers(moduleTestAnswer);
//        返回添加模块测试题页面
        return "redirect:/shiyan/addLastQuest";
    }


    /**
     * 整体模块测试题题目列表
     *
     * @param session 数据的缓存
     * @param page    分页信息
     * @param model   绑定参数给前端传值
     * @return 返回到静态资源下的shiyan/lookLastTest.html
     */
    @RequestMapping("lastTestList")
    public String list(HttpSession session,
                       @RequestParam(value = "page", defaultValue = "0", required = true) Integer page,
                       Model model) {
//        分页数据的条数为10，即没10条数据进行分页
        Pageable pageable = PageRequest.of(page, 10);
//        分页的条件是以模块id，即mid为条件分页
        Page<ModuleTestQuest> termList = questService.findByLastPage(pageable, -1);
//        清理添加模块测试题数据回显的缓存，以便下次点击添加时时干净的页面
        String questDescribe = "";
        model.addAttribute("questDescribe", questDescribe);
        String questScore = "";
        model.addAttribute("questScore", questScore);
        String questAnswer = "";
        model.addAttribute("questAnswer", questAnswer);
        String questOrder = "";
        model.addAttribute("questOrder", questOrder);

        session.removeAttribute("errorInformation");
        session.removeAttribute("badAnswer");

//        将分页信息传给前端
        model.addAttribute("termList", termList);
        return "shiyan/lookLastTest";
    }


    /**
     * 删除整体模块测试题
     *
     * @param questId 需要删除的模块测试题的问题id
     * @return 返回整体测试题列表
     */
    @RequestMapping("deleteLastQuest/{questId}")
    public String deleteQuest(@PathVariable("questId") int questId) {
//        删除学生答题记录
        moduleTestAnswerStuService.deleteByQuestId(questId);
//        删除该问题的所有选项
        List<ModuleTestAnswer> m = answerService.findAllByQuestId(questId);
        for (ModuleTestAnswer moduleTestAnswer : m) {
            answerService.deleteAnswer(moduleTestAnswer.getAnswerId());
        }
//        删除整体模块测试题的问题id
        questService.deleteQuest(questId);
//        更新学生成绩
        scoreUpdateService.allStudentScoreUpdate();
        return "redirect:/shiyan/lastTestList";
    }

    /**
     * 修改整体测试题的试题信息
     *
     * @param questId 需要修改的模块测试题的问题id
     * @param model   绑定参数给前端传值
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
     *
     * @param questId       需要修改的模块测试题的问题id
     * @param quest         ModuleTestQuest对象
     * @param model         绑定参数给前端传值
     * @return 返回整体测试题列表
     */
    @PostMapping("updateLastQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId,
                              ModuleTestQuest quest,HttpSession session,
                              Model model) {
//        通过问题id找到这个问题并存入ModuleTestQuest对象
        ModuleTestQuest quest1 = questService.findQuestByQuestId(questId);
//        先获取修改模块测试信息get方法中修改的内容，再将修改好的题目信息更新到ModuleTestQuest对象
        quest1.setQuestDescribe(quest.getQuestDescribe());
        quest1.setQuestAnswer(quest.getQuestAnswer());
        quest1.setQuestType(quest.getQuestType());
        quest1.setQuestScore(quest.getQuestScore());


//        多选题答案验证
        String dana = quest1.getQuestAnswer();
        String[] daanList = dana.split(",");
        for (String a : daanList) {
            System.out.println(a);
            try {
                Integer.parseInt(a);

            }catch (Exception e){
                session.setAttribute("badAnswer","输入的答案包含非法字符");
                return "redirect:/shiyan/updateQuest/"+questId;
            }
        }
//        选择题目类型和答案对比

        String a = quest1.getQuestType();
        String b = quest1.getQuestAnswer();
        if (a.equals("单选")){
            try {
                Integer.parseInt(b);
                questService.addModuleTestQuest(quest1);
            }
            catch (Exception e)
            {
                session.setAttribute("errorInformation","单选答案必须是数字");
                return "redirect:/shiyan/updateLastQuest/"+questId;
            }
        }else{
//            利用questService里的保存方法，将数据存到数据库
            questService.addModuleTestQuest(quest1);
        }
//        更新学生成绩
        scoreUpdateService.allStudentScoreUpdate();
        return "redirect:/shiyan/lastTestList";
    }

    /**
     * 在修改整体模块测试题中增加选项
     *
     * @param model   绑定参数给前端传值
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
     *
     * @param questId        需要修改的模块测试题的问题id
     * @param answerDescribe 问题选项内容
     * @param answerOrder    问题选项序号
     * @return 返回修改整体测试题的试题信息页面
     */
    @PostMapping("upLastAnswer/{questId}")
    public String upAnswer(@PathVariable("questId") int questId,
                           String answerDescribe, int answerOrder,
                           HttpSession session) {
//        实例化一个ModuleTestAnswer对象
        ModuleTestAnswer answer = new ModuleTestAnswer();
//        添加问题选项的信息到ModuleTestAnswer对象
        answer.setAnswerDescribe(answerDescribe);
        answer.setAnswerOrder(answerOrder);
//        将参数当前问题id存到ModuleTestAnswer对象
        answer.setQuestId(questId);
//        将新增的选项存到数据库
        answerService.addAnswers(answer);
//        清除提示缓存
        session.removeAttribute("errorInformation");
//        更新学生成绩
        scoreUpdateService.allStudentScoreUpdate();
        return "redirect:/shiyan/updateLastQuest/" + questId;
    }

    /**
     * 删除添加整体模块测试题页面的选项
     *
     * @param answerId 需要删除的模块测试题的问题id
     * @return 返回添加整体测试题息页面
     */
    @RequestMapping("deleteLastAnswer/{answerId}")
    public String deleteAnswer(@PathVariable("answerId") int answerId) {
//        调用answerService的方法删除选项id来删除选项
        answerService.deleteAnswer(answerId);
        return "redirect:/shiyan/addLastQuest";
    }

    /**
     * 删除修改整体模块测试题页面的选项
     *
     * @param answerId 需要删除的模块测试题的问题id
     * @return 返回添加整体测试题息页面
     */
    @RequestMapping("deleteLastUpAnswer/{answerId}")
    public String deleteUpAnswer(@PathVariable("answerId") int answerId) {
//        通过调用answerService的方法，用选项id找到问题id
        int qId = answerService.findQuestIdByAnswerId(answerId);
//        调用answerService的方法删除选项id来删除选项
        answerService.deleteAnswer(answerId);
//        更新学生成绩
        scoreUpdateService.allStudentScoreUpdate();
        return "redirect:/shiyan/updateLastQuest/" + qId;
    }


}