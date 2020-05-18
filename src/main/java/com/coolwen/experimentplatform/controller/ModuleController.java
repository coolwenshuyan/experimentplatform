package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.model.ReportAnswer;
import com.coolwen.experimentplatform.service.ModuleTestAnswerService;
import com.coolwen.experimentplatform.service.ModuleTestQuestService;
import com.coolwen.experimentplatform.service.ReportAnswerService;
import com.coolwen.experimentplatform.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 14:45
 */
@Controller
@RequestMapping("/shiyan")
@SessionAttributes("title")
public class ModuleController {

    @Autowired
    private ModuleTestQuestService questService;

    @Autowired
    private ModuleTestAnswerService answerService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportAnswerService reportAnswerService;


    /**
     * 添加实验模块试题
     *
     * @return
     */

    @PostMapping("addTest")
    public String addQuest(ModuleTestQuest moduleTestQuest, HttpSession session, String questType, float questScore, String questAnswer, int mId) {
//        在试题表添加试题信息
        String title = (String) session.getAttribute("title");
        moduleTestQuest = questService.findByQuestDescribe(title);
        moduleTestQuest.setQuestType(questType);
        moduleTestQuest.setQuestScore(questScore);
        moduleTestQuest.setQuestAnswer(questAnswer);
        moduleTestQuest.setmId(mId);
        System.out.println(moduleTestQuest);
        questService.addModuleTestQuest(moduleTestQuest);
        return "redirect:/shiyan/list";
    }

    //试题列表
    @RequestMapping("list")
    public String list(HttpSession session,
//                       @RequestParam(value = "page", defaultValue = "0") Integer page,
//                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum,
                       Model model) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum, 10);
        Page<ModuleTestQuest> pageList = questService.findByPage(pageable);

        String title = "";
        model.addAttribute("title", title);
        System.out.println(session.getAttribute("title"));
        System.out.println("分页信息：" + pageList);

//        model.addAttribute("search", search);
        model.addAttribute("questsPage", pageList);
//        model.addAttribute("quests", questService.loadAll());
        return "shiyan/lookTest";
    }


    //删除试题
    @RequestMapping("deleteQuest/{questId}")
    public String deleteQuest(@PathVariable("questId") int questId) {
        System.out.println(questId);
        System.out.println(questService.findQuestByQuestId(questId));
        questService.deleteQuest(questId);
        return "redirect:/shiyan/list";
    }

    /**
     * 修改试题信息
     *
     * @param questId
     * @param model
     * @return
     */
    @GetMapping("updateQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId, Model model, HttpSession session) {
        String title = (String) session.getAttribute("questDescribe");
        String title1 = (String) session.getAttribute("questScore");
        String title2 = (String) session.getAttribute("questType");
        String title3 = (String) session.getAttribute("questAnswer");
        ModuleTestQuest quest = questService.findQuestByQuestId(questId);
        if (title == null && title1 == null && title2 == null && title3 == null) {
            model.addAttribute("UpQuest", quest);
        }

        List<ModuleTestAnswer> UpAnswer = answerService.findAllByQuestId(quest.getQuestId());
        model.addAttribute("UpQuest", quest);
        model.addAttribute("UpAnswer", UpAnswer);
        model.addAttribute("qid", questId);
        return "shiyan/updateTest";
    }

    @PostMapping("updateQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId, HttpSession session,
                              ModuleTestQuest quest, ModuleTestAnswer answer,
                              Model model, String questDescribe, String answerDescribe) {
        System.out.println("q----------------->>>>>>>>>>>>>>>>" + quest);
        ModuleTestQuest quest1 = questService.findQuestByQuestId(questId);
        quest1.setQuestDescribe(quest.getQuestDescribe());
        quest1.setQuestAnswer(quest.getQuestAnswer());
        quest1.setQuestType(quest.getQuestType());
        quest1.setQuestScore(quest.getQuestScore());
        model.addAttribute("UpQuest", quest1);
        model.addAttribute("qid", questId);
        questService.addModuleTestQuest(quest1);

        model.addAttribute("questDescribe", questDescribe);
//        return "redirect:/shiyan/updateQuest/{questId}";
        return "redirect:/shiyan/list";
    }

//    修改试题中增加选项

    @GetMapping("upAnswer/{questId}")
    public String upAnswer(@PathVariable("answerId") int answerId, Model model, int questId) {

        ModuleTestQuest quest = questService.findQuestByQuestId(questId);
        List<ModuleTestAnswer> upAnswer = answerService.findAllByQuestId(quest.getQuestId());
        model.addAttribute("UpAnswer", upAnswer);
        int qId = answerService.findQuestIdByAnswerId(answerId);
        return "redirect:/shiyan/updateQuest/" + qId;
    }

    @PostMapping("upAnswer/{questId}")
    public String upAnswer(@PathVariable("questId") int questId,
                           String answerDescribe, int answerOrder) {
        System.out.println(questId);
        ModuleTestAnswer answer = new ModuleTestAnswer();
        answer.setAnswerDescribe(answerDescribe);
        answer.setAnswerOrder(answerOrder);
        answer.setQuestId(questId);
        answerService.addAnswers(answer);
        return "redirect:/shiyan/updateQuest/" + questId;
    }


//删除选项

    @RequestMapping("deleteAnswer/{answerId}")
    public String deleteAnswer(@PathVariable("answerId") int answerId, Model model) {
        List answerList = answerService.findAllByAnswerId(answerId);
        for (int i = 0; i < answerList.size(); i++) {
            ModuleTestAnswer answer = (ModuleTestAnswer) answerList.get(i);
            System.out.println("打印找到的每个answerId————————" + answer.getAnswerId());
            if (answer.getAnswerId() == answerService.findAnswerId(answerId)) {
                System.out.println("找到了要删除的ID————————" + answer.getAnswerId());
                answerService.deleteAnswer(answer.getAnswerId());
            }
        }
        return "redirect:/shiyan/addAnswer";
    }


    //删除更新选项

    @RequestMapping("deleteUpAnswer/{answerId}")
    public String deleteUpAnswer(@PathVariable("answerId") int answerId) {
        int qId = answerService.findQuestIdByAnswerId(answerId);
        answerService.deleteAnswer(answerId);
        return "redirect:/shiyan/updateQuest/" + qId;
    }


//    以题目或者实验模块号的模糊查询

    @RequestMapping("findQuest")
    public String loadQuest(Model model, String questDescribe, int mId) {
        model.addAttribute("findQuest", questService.load(questDescribe, mId));
        return "questList";
    }

    //    以实验模块号查询试题

    @RequestMapping("findQuest/{mId}")
    public String findQ(@PathVariable("mId") int mId, Model model, String search) {
        model.addAttribute("search", search);
//        model.addAttribute("findQ",questService.find(mId));
        System.out.println(questService.find(mId));
        return "redirect:/shiyan/list";
    }


//    //    试题列表，以实验模块id（mid）、试题序号（questOrder）排序
//
//    @RequestMapping("questList")
//    public String loadAll(Model model, ModuleTestQuest moduleTestQuest) {
//
////        model.addAttribute("Quest", questService.loadAll());
//        return "shiyan/lookTest";
//    }


    //添加选项
    @GetMapping("addAnswer")
    public String addAnswer(Model model, HttpSession session, ModuleTestAnswer moduleTestAnswer, ModuleTestQuest moduleTestQuest) {
        String title = (String) session.getAttribute("title");
        if (title == null || title.isEmpty() || title == "") {
            model.addAttribute("addAnswer", new ModuleTestAnswer());
            model.addAttribute("quest", new ModuleTestQuest());
        } else {
            System.out.println("打印else____" + questService.findByQuestDescribe(title));
            ModuleTestQuest quest = questService.findByQuestDescribe(title);
            List<ModuleTestAnswer> addAnswer = answerService.findAllByQuestId(quest.getQuestId());
            System.out.println("getELSE测试选项_-__" + moduleTestAnswer);
            System.out.println("getELSE测试题目_-__" + addAnswer);
            model.addAttribute("addAnswer", addAnswer);

        }
        System.out.println("前端显示的题目打印______" + title);
        return "shiyan/addTest";
    }

    @PostMapping("addAnswer")
    public String addAnswer(String title, Model model, ModuleTestAnswer moduleTestAnswer, ModuleTestQuest moduleTestQuest, HttpSession session) {

        moduleTestQuest.setQuestDescribe(title);
        System.out.println("title:-------" + title);
        System.out.println("测试选项————" + moduleTestAnswer);
        System.out.println("测试题目————" + moduleTestQuest);

        String Stitle = (String) session.getAttribute("title");
        System.out.println("Stitle:>>>>>>>>" + Stitle);
        if (Stitle == null || Stitle.isEmpty() || Stitle == "") {
            questService.addModuleTestQuest(moduleTestQuest);
        }

        moduleTestQuest = questService.findByQuestDescribe(title);
        System.out.println("查询后——————" + moduleTestQuest);
        moduleTestAnswer.setQuestId(moduleTestQuest.getQuestId());
        answerService.addAnswers(moduleTestAnswer);

        model.addAttribute("title", title);
        System.out.println("这是post打印的题目————————" + title);
        return "redirect:/shiyan/addAnswer";
    }


//全部试题选项

//    @RequestMapping("answerList")
//    public String loadAswers(Model model) {
//        model.addAttribute("answers", answerService.answerList());
//        return "answerList";
//    }

//实验报告问题增加

    @GetMapping("addReport")
    public String addReport(Model model,
                            @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 10);
        Page<Report> reportList = reportService.findByReportPage(pageable);

//        List<Report> addReport = reportService.loadReport();

        model.addAttribute("addReport", reportList);
        return "shiyan/part";
    }

    @PostMapping("addReport")
    public String addReport(Report report) {
        reportService.addReport(report);
        return "redirect:/shiyan/addReport";
    }

//  删除实验报告

    @RequestMapping("/deleteReport/{reportId}")
    public String deleteReport(@PathVariable("reportId") String reportId) {
        System.out.println("——————————————————" + reportId);
        reportService.deleteReport(Integer.parseInt(reportId));
        return "redirect:/shiyan/addReport";
    }

    /**
     * 修改实验信息
     *
     * @param reportId
     * @param model
     * @return
     */
    @GetMapping("updateReport/{reportId}")
    public String updateReport(@PathVariable("reportId") int reportId, Model model) {
        Report report = reportService.updateReport(reportId);

        model.addAttribute("Upreport", report);
        return "shiyan/updatePart";
    }

    @PostMapping("updateReport/{reportId}")
    public String updateReport(@PathVariable("reportId") int reportId, Report report) {
        Report r = reportService.updateReport(reportId);
        r.setReportDescribe(report.getReportDescribe());
        r.setReportType(report.getReportType());
        r.setReportScore(report.getReportScore());
        r.setReportOrder(report.getReportOrder());
        r.setmId(report.getmId());
        reportService.addReport(r);
        return "redirect:/shiyan/addReport";
    }


//    查询所有实验

    @RequestMapping("findReport")
    public String loadReport(Model model) {
        model.addAttribute("reports", reportService.loadReport());
        return "reportList";
    }


//学生填写实验报告

    @GetMapping("addReportAnswer")
    public String addReportAnswer(Model model) {
        model.addAttribute("RAnswer", new ReportAnswer());
        return "addReportAnswer";
    }

    @PostMapping("addReportAnswer")
    public String addReportAnswer(ReportAnswer reportAnswer) {
        reportAnswerService.addReportAnswer(reportAnswer);
        return "reportAnswerList";
    }

    //删除实验
    @RequestMapping("/ReportAnswer/{id}")
    public String deleteReportAnswer(@PathVariable("id") int id) {
        reportAnswerService.deleteReportAnswer(id);
        return "reportAnswerList";
    }


    /**
     * 学生修改实验报告
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("updateReportAnswer/{id}")
    public String updateReportAnswer(@PathVariable("id") int id, Model model) {
        ReportAnswer UpAnswer = reportAnswerService.updateReportAnswer(id);
        model.addAttribute("Upreport", UpAnswer);
        return "updateReportAnswer";
    }

    @PostMapping("updateReportAnswer/{id}")
    public String updateReportAnswer(@PathVariable("id") int id, ReportAnswer reportAnswer) {
        ReportAnswer R = reportAnswerService.updateReportAnswer(id);
        R.setStuReportAnswer(reportAnswer.getStuReportAnswer());
        reportAnswerService.addReportAnswer(R);
        return "redirect:/reportAnswerList";
    }
//
//    //    查询所有实验
//    @RequestMapping("/loadReport")
//    public List<Report> loadReport(Model model) {
//        Report reports = new Report();
//        model.addAttribute("reports",reports);
//        return reportService.loadReport();
//    }
//


}