package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestAnswerStu;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.service.*;
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
 * 后台管理系统：实验模块测试题目、选项的增删改查；实验报告的增删改查
 */
@Controller
@RequestMapping("/shiyan")
//设置数据回显
@SessionAttributes(value = {"title", "questDescribe", "questType", "questScore", "questAnswer", "questOrder"})
public class ModuleController {

    /**
     * 注入模块测试题、测试题的选项、实验报告的service、学生成绩更新的和学生答题表的service、学生实验报告答题表
     */
    @Autowired
    private ModuleTestQuestService questService;

    @Autowired
    private ModuleTestAnswerService answerService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ScoreUpdateService scoreUpdateService;

    @Autowired
    private ModuleTestAnswerStuService moduleTestAnswerStuService;

    @Autowired
    private ReportAnswerService reportAnswerService;

    /**
     * 添加实验模块试题
     *
     * @param model   绑定参数给前端thymeleaf传值
     * @param session 存储、获取一些需要的值
     * @return 返回到静态资源下的shiyan/addTest.html
     */
    @GetMapping("addQuest")
    public String addQuest(Model model, HttpSession session ) {


//        从缓存中取到questDescribe，即题目的信息
        String questDescribe = (String) session.getAttribute("questDescribe");
        System.out.println("打印题目信息~~~~~~" + questDescribe);
//        从缓存中取到mId
        int mId = (int) session.getAttribute("mId");

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
//                System.out.println("————————————stuList" + stuList);
//            }
        }
//        如果stuList列表为空
        if (stuList == null || stuList.isEmpty() || CollectionUtils.isEmpty(stuList)) {
//            判断题目是否为空，如果为空就允许添加对象
            if (questDescribe == null || questDescribe.isEmpty() || questDescribe == "") {
                model.addAttribute("addAnswer", new ModuleTestAnswer());
                model.addAttribute("quest", new ModuleTestQuest());

            } else {

//                如果题目不为空，先从session中取到在添加试题的post方法里存入的questId，即问题id
                int qId = (int) session.getAttribute("questId");
//                定义一个quest对象，并以当前缓存的questId查找这个题目的所有信息赋值给quest
                ModuleTestQuest quest = questService.findQuestByQuestId(qId);
//                再用那个questId查找对应题目的选项，并存入ModuleTestAnswer的list里面
                List<ModuleTestAnswer> addAnswer = answerService.findAllByQuestId(qId);
//                传递查出来的参数，将数据和前端绑定
                model.addAttribute("addAnswer", addAnswer);
                model.addAttribute("quest", quest);
            }

            model.addAttribute("mId", mId);
//            返回到静态资源下的shiyan/addTest.html
            return "shiyan/addTest";
        } else {

//            如果列表不为空，就返回信息
            System.out.println("不允许作答————————");
            String message = "学生已作答，不允许添加试题";
            session.setAttribute("msg2020612", message);
            return "redirect:/shiyan/list/" + mId;

        }
    }


    /**
     * 添加实验模块试题的post方法
     *
     * @param moduleTestQuest 题目信息
     * @param session         存储、获取一些需要的值
     * @param model           绑定参数给前端thymeleaf传值
     * @param questDescribe   题目
     * @param questType       题目类型
     * @param questScore      题目分数
     * @param questAnswer     题目答案
     * @param questOrder      题目序号
     * @return 返回当前添加试题页面
     */
    @PostMapping("addQuest")
    public String addQuest(ModuleTestQuest moduleTestQuest, HttpSession session, Model model,
                           String questDescribe, String questType, float questScore, String questAnswer,
                           int questOrder) {
//        在试题表添加试题信息
//      获取在shiyan/list/{mId}这个路径方法里存的mid
        int id = (int) session.getAttribute("mId");
//        完成添加题目、题目类型、题目分数、题目答案、题目序号的操作
        moduleTestQuest.setQuestDescribe(questDescribe);
        moduleTestQuest.setQuestType(questType);
        moduleTestQuest.setQuestScore(questScore);
        moduleTestQuest.setQuestAnswer(questAnswer);
        moduleTestQuest.setQuestOrder(questOrder);
//        添加题目的mid，为前面从list/{mid}里取到的mid
        moduleTestQuest.setmId(id);
//        在控制台打印得到的这个moduleTestQuest对象
        System.out.println(moduleTestQuest);
//        利用questService里的保存方法，将数据存到数据库
        questService.addModuleTestQuest(moduleTestQuest);
//        控制台打印看添加进去的问题id是多少
        System.out.println("添加测试题里面的questID~~~~~~" + moduleTestQuest.getQuestId());
//        将这个问题id存入session
        session.setAttribute("questId", moduleTestQuest.getQuestId());

//        利用model绑定数据到前端，实现数据回显
        model.addAttribute("questDescribe", questDescribe);
        model.addAttribute("questType", questType);
        model.addAttribute("questScore", questScore);
        model.addAttribute("questAnswer", questAnswer);
        model.addAttribute("questOrder", questOrder);

//        返回当前添加试题页面
        return "redirect:/shiyan/addQuest";
    }

    /**
     * 模块测试题的试题列表
     *
     * @param session 存储、获取一些需要的值
     * @param mId     从实验模块管理页面传来的参数，模块id——mid
     * @param pageNum 分页信息
     * @param model   与前端的数据绑定、交互
     * @return 返回静态资源下的shiyan/lookTest.html
     */
    @RequestMapping("list/{mId}")
    public String list(HttpSession session,
                       @PathVariable("mId") int mId,
                       @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum,
                       Model model) {
//        分页数据的条数为10，即没10条数据进行分页
        Pageable pageable = PageRequest.of(pageNum, 10);
//        分页的条件是以传过来的参数mid为条件分页
        Page<ModuleTestQuest> pageList = questService.findByExpPage(mId, pageable);

//        清理添加模块测试题数据回显的缓存，以便下次点击添加时时干净的页面
        String questDescribe = "";
        model.addAttribute("questDescribe", questDescribe);
        String questScore = "";
        model.addAttribute("questScore", questScore);
        String questAnswer = "";
        model.addAttribute("questAnswer", questAnswer);
        String questOrder = "";
        model.addAttribute("questOrder", questOrder);


//        将分页信息存到model传给前端
        model.addAttribute("questsPage", pageList);
//        将这个页面传来的参数存到model传给前端进行分页的页面路径绑定
        model.addAttribute("mId", mId);
//        将这个页面传来的参数存到session中，为后面添加试题、修改、删除试题提供mid的值
        session.setAttribute("mId", mId);
//        返回静态资源下的shiyan/lookTest.html
        return "shiyan/lookTest";
    }


    /**
     * 删除模块测试题
     *
     * @param questId 需要删除的问题id
     * @return 返回模块测试题的试题列表
     */
    @RequestMapping("deleteQuest/{questId}")
    public String deleteQuest(@PathVariable("questId") int questId) {
//        在控制台打印找到的问题id
        System.out.println(questService.findQuestByQuestId(questId));
//        将通过问题id查找到的那个问题存入moduleTestQuest对象中，为后面返回试题列表传递mid
        ModuleTestQuest moduleTestQuest = questService.findQuestByQuestId(questId);
//        删除学生答题记录
        moduleTestAnswerStuService.deleteByQuestId(questId);
//        删除该问题的所有选项
        List<ModuleTestAnswer> m = answerService.findAllByQuestId(questId);
        for (ModuleTestAnswer moduleTestAnswer : m) {
            answerService.deleteAnswer(moduleTestAnswer.getAnswerId());
        }
//        调用questService中的方法删除问题id，就是问题的主键
        questService.deleteQuest(questId);
//        更新学生信息
        scoreUpdateService.allStudentScoreUpdate();
//        通过moduleTestQuest找到问题的mid，返回试题列表
        return "redirect:/shiyan/list/" + moduleTestQuest.getmId();
    }

    /**
     * 修改模块测试的信息
     *
     * @param questId 获取需要修改的问题id，并传入修改路径
     * @param model   与前端的数据绑定、交互
     * @return 返回静态资源下的shiyan/updateTest.html
     */
    @GetMapping("updateQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId, Model model) {
//        通过问题id找到这个问题并存入ModuleTestQuest对象
        ModuleTestQuest quest = questService.findQuestByQuestId(questId);
//        通过问题id找到问题的选项，并存入list中，主要用来绑定数据到前端显示
        List<ModuleTestAnswer> UpAnswer = answerService.findAllByQuestId(questId);
//        用model绑定找到的问题，传给前端
        model.addAttribute("UpQuest", quest);
//        用model绑定找到的问题选项，传给前端
        model.addAttribute("UpAnswer", UpAnswer);
//        用model绑定问题id，传给前端
        model.addAttribute("qid", questId);
//        返回静态资源下的shiyan/updateTest.html
        return "shiyan/updateTest";
    }

    /**
     * 修改模块测试信息的post方法
     *
     * @param questId 获取需要修改的问题id，并传入修改路径
     * @param quest   ModuleTestQuest的对象
     * @return 返回模块测试题的列表
     */
    @PostMapping("updateQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId,
                              ModuleTestQuest quest) {
//        通过问题id找到这个问题并存入ModuleTestQuest对象
        ModuleTestQuest quest1 = questService.findQuestByQuestId(questId);
//        先获取修改模块测试信息get方法中修改的内容，再将修改好的题目更新到ModuleTestQuest对象
        quest1.setQuestDescribe(quest.getQuestDescribe());
//        先获取修改模块测试信息get方法中修改的内容，再将修改好的题目答案更新到ModuleTestQuest对象
        quest1.setQuestAnswer(quest.getQuestAnswer());
//        先获取修改模块测试信息get方法中修改的内容，再将修改好的题目类型更新到ModuleTestQuest对象
        quest1.setQuestType(quest.getQuestType());
//        先获取修改模块测试信息get方法中修改的内容，再将修改好的题目分数更新到ModuleTestQuest对象
        quest1.setQuestScore(quest.getQuestScore());
//        先获取修改模块测试信息get方法中修改的内容，再将修改好的题目序号更新到ModuleTestQuest对象
        quest1.setQuestOrder(quest.getQuestOrder());
//        调用questService的方法更新数据
        questService.addModuleTestQuest(quest1);
//        更新学生成绩
        scoreUpdateService.allStudentScoreUpdate();
//        返回模块测试题的列表
        return "redirect:/shiyan/list/" + quest1.getmId();
    }

    /**
     * 修改模块测试题中的选项
     *
     * @param model   与前端的数据绑定、交互
     * @param questId 获取需要修改的问题id，并传入修改路径
     * @return 返回修改模块测试信息的页面
     */
    @GetMapping("upAnswer/{questId}")
    public String upAnswer(@PathVariable("questId") int questId, Model model) {
//       通过问题id找到这个问题并存入ModuleTestQuest对象
        ModuleTestQuest quest = questService.findQuestByQuestId(questId);
//        通过问题id找到问题的选项，并存入list中，主要用来绑定数据到前端显示
        List<ModuleTestAnswer> upAnswer = answerService.findAllByQuestId(quest.getQuestId());
//        将找到并存入的list传给前端
        model.addAttribute("UpAnswer", upAnswer);
//        将找到的问题id传入路径，返回修改模块测试信息的页面
        return "redirect:/shiyan/updateQuest/" + questId;
    }

    /**
     * 修改模块测试题中选项修改的post方法
     *
     * @param questId        获取需要修改的问题id，并传入修改路径
     * @param answerDescribe 选项内容
     * @param answerOrder    选项序号
     * @return 返回修改模块测试信息的页面
     */
    @PostMapping("upAnswer/{questId}")
    public String upAnswer(@PathVariable("questId") int questId,
                           String answerDescribe, int answerOrder) {
//        在控制台打印传入的参数问题id
        System.out.println(questId);
//        实例化一个ModuleTestAnswer对象
        ModuleTestAnswer answer = new ModuleTestAnswer();
//        新增问题选项的序号到ModuleTestAnswer对象
        answer.setAnswerDescribe(answerDescribe);
//        新增问题选项的内容到ModuleTestAnswer对象
        answer.setAnswerOrder(answerOrder);
//        将参数当前问题id存到ModuleTestAnswer对象
        answer.setQuestId(questId);
//        将新增的选项存到数据库
        answerService.addAnswers(answer);
//        返回修改模块测试信息的页面
        return "redirect:/shiyan/updateQuest/" + questId;
    }


    /**
     * 删除添加模块测试题中的选项
     *
     * @param answerId 需要删除的选项id
     * @return 返回添加模块测试题的页面
     */
    @RequestMapping("deleteAnswer/{answerId}")
    public String deleteAnswer(@PathVariable("answerId") int answerId) {
//        调用answerService的方法删除选项id来删除选项
        answerService.deleteAnswer(answerId);
//        返回添加模块测试题的页面
        return "redirect:/shiyan/addQuest";
    }


    /**
     * 删除修改模块测试题中的选项
     *
     * @param answerId answerId 需要删除的选项id
     * @return 返回修改模块测试信息的页面
     */
    @RequestMapping("deleteUpAnswer/{answerId}")
    public String deleteUpAnswer(@PathVariable("answerId") int answerId) {
//        通过调用answerService的方法，用选项id找到问题id
        int qId = answerService.findQuestIdByAnswerId(answerId);
//        调用answerService的方法删除选项id来删除选项
        answerService.deleteAnswer(answerId);
//        返回修改模块测试信息的页面
        return "redirect:/shiyan/updateQuest/" + qId;
    }


    /**
     * 在添加模块测试题中添加选项
     *
     * @return 返回静态资源下的shiyan/addAnswer.html
     */
    @GetMapping("addAnswer")
    public String addAnswer() {
//       返回静态资源下的shiyan/addAnswer.html
        return "shiyan/addAnswer";
    }

    /**
     * 在添加模块测试题中添加选项的post方法
     *
     * @param moduleTestAnswer 模块测试题的选项对象
     * @param session          数据的缓存空间
     * @return 返回添加模块测试题页面
     */
    @PostMapping("addAnswer")
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
        return "redirect:/shiyan/addQuest";
    }

    /**
     * 添加模块测试的实验报告
     *
     * @param model   与前端的数据绑定、交互
     * @param session 数据的缓存空间
     * @return 返回静态资源下的shiyan/part-add.html
     */
    @GetMapping("addReport")
    public String addReport(Model model, HttpSession session) {

//        将实验报告列表中存入的mid取出，并赋值给id
        int id = (int) session.getAttribute("mId");
//        将mid传给前端做路径
        model.addAttribute("mId", id);
        return "shiyan/part-add";
    }

    /**
     * 添加模块测试的实验报告的post方法
     *
     * @param report         实验报告对象
     * @param session        数据的缓存空间
     * @param reportDescribe 实验报告的题目
     * @param reportOrder    实验报告的序号
     * @param reportType     实验报告的类型
     * @param reportScore    实验报告的分数
     * @return 返回实验报告的列表
     */
    @PostMapping("addReport")
    public String addReport(Report report, HttpSession session,
                            String reportDescribe, int reportOrder, String reportType, float reportScore) {
//        将实验报告列表中存入的mid取出，并赋值给id
        int id = (int) session.getAttribute("mId");
//        将实验报告的序号、题目、类型、分数存入Report对象
        report.setReportOrder(reportOrder);
        report.setReportDescribe(reportDescribe);
        report.setReportType(reportType);
        report.setReportScore(reportScore);
//        把对象Report的mid设为当前模块的mid
        report.setmId(id);
//        将Report对象的数据存入数据库
        reportService.addReport(report);
//        返回实验报告的列表
        return "redirect:/shiyan/reportList/" + report.getmId();
    }

    /**
     * 删除实验报告
     *
     * @param reportId 传入要删除的实验报告的id
     * @return 返回实验报告的列表页面
     */
    @RequestMapping("deleteReport/{reportId}")
    public String deleteReport(@PathVariable("reportId") int reportId) {
//        控制台打印要删除的实验报告id
        System.out.println("——————————————————" + reportId);
//        调用reportService的方法，通过实验报告的id找到实验报告，并存入一个Report对象
        Report report = reportService.findByReportId(reportId);
//        调用reportService的方法，删除实验报告的id
        reportService.deleteReport(reportId);

//        删除学生填写的相应的实验报告答题记录
        reportAnswerService.deleteReportAnswerByReportId(reportId);
//        更新学生成绩
        scoreUpdateService.allStudentScoreUpdate();
//        返回实验报告的列表页面
        return "redirect:/shiyan/reportList/" + report.getmId();
    }

    /**
     * 修改实验报告信息
     *
     * @param reportId 传入要修改的实验报告的id
     * @param model    与前端的数据绑定、交互
     * @return 返回静态资源下的shiyan/updatePart.html
     */
    @GetMapping("updateReport/{reportId}")
    public String updateReport(@PathVariable("reportId") int reportId, Model model) {
//        调用reportService的方法，通过实验报告的id找到实验报告，并存入一个Report对象
        Report report = reportService.updateReport(reportId);
//        将找到的report对象传给前端
        model.addAttribute("Upreport", report);
//        返回静态资源下的shiyan/updatePart.html
        return "shiyan/updatePart";
    }

    /**
     * 修改实验报告信息的post方法
     *
     * @param reportId 传入要修改的实验报告的id
     * @param report   实验报告对象
     * @return 返回实验报告列表
     */
    @PostMapping("updateReport/{reportId}")
    public String updateReport(@PathVariable("reportId") int reportId, Report report) {
//        调用reportService的方法，通过实验报告的id找到实验报告，并存入一个Report对象
        Report r = reportService.updateReport(reportId);
//        将前端修改的内容更新到Report对象中
        r.setReportDescribe(report.getReportDescribe());
        r.setReportType(report.getReportType());
        r.setReportScore(report.getReportScore());
        r.setReportOrder(report.getReportOrder());
//        将Report对象中的数据更新到数据库
        reportService.addReport(r);
//        返回实验报告列表
        return "redirect:/shiyan/reportList/" + r.getmId();
    }

    /**
     * 查询当前模块测试的实验报告
     *
     * @param model   与前端的数据绑定、交互
     * @param session 数据的缓存空间
     * @param mId     传入当前模块id
     * @param pageNum 分页信息
     * @return 返回静态资源下的shiyan/part-list.html
     */
    @RequestMapping("reportList/{mId}")
    public String loadReport(Model model,
                             HttpSession session,
                             @PathVariable("mId") int mId,
                             @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
//        分页数据条数为10
        Pageable pageable = PageRequest.of(pageNum, 10);
//        根据mid为条件进行分页
        Page<Report> reportList = reportService.findByReportPage(pageable, mId);
//        将传入的参数mid存入session
        session.setAttribute("mId", mId);
//        把分页信息传给前端
        model.addAttribute("reports", reportList);
//        把mid传给前端
        model.addAttribute("mId", mId);
//        返回静态资源下的shiyan/part-list.html
        return "shiyan/part-list";
    }


}