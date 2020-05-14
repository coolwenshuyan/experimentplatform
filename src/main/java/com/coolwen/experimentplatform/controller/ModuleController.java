package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import com.coolwen.experimentplatform.service.ModuleTestAnswerService;
import com.coolwen.experimentplatform.service.ModuleTestQuestService;
import com.coolwen.experimentplatform.service.ReportAnswerService;
import com.coolwen.experimentplatform.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
//@ResponseBody
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
    @GetMapping("/addQuest")
    public String addQuest() {
        return "/addQuest";
    }

    @PostMapping("/addQuest")
    public String addQuest(String questDescribe, String questType, String questAnswer, float questScore, int questOrder, int mId) {
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
     *
     * @param questId
     * @param model
     * @return
     */
    @GetMapping("/updateQuest/{questId}")
    public String updateQuest(@PathVariable("questId") int questId, Model model) {
        ModuleTestQuest quest = questService.update(questId);
//        model.addAttribute("quest",quest);
        return "/updateQuest";
    }

    @PostMapping("/updateQuest/{questId}")
    public String update(@PathVariable("questId") int questId, ModuleTestQuest quest) {
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
    public String deleteQuest(@PathVariable("questId") int questId) {
        questService.deleteQuest(questId);
        return "/questList";
    }

    //    以题目模糊查询
    @RequestMapping("/loadQuest")
    public List<ModuleTestQuest> loadQuest(String questDescribe,Model model) {
        ModuleTestQuest loadQuest = new ModuleTestQuest();
        model.addAttribute("loadQuest",loadQuest);
        return questService.load(questDescribe);
    }

    //    试题列表，以实验模块id（mid）、试题序号（questOrder）排序
    @RequestMapping("/loadAll")
    public List<ModuleTestQuest> loadAll() {
        return questService.loadAll();
    }

    @GetMapping("/addAnswer")
    public String addAnswer(){
        return "/addAnswer";
    }


//添加选项
    @PostMapping("/addAnswer")
    public String addAnswer(Model model,String answerDescribe,int answerOrder,int questId) {
        //存储输入的问题id号
        ModuleTestAnswer a =answerService.findByQuestId(questId);
        List<ModuleTestAnswer> list = new ArrayList<>();
        ModuleTestAnswer answer = new ModuleTestAnswer();
        for (int i = 0; i < list.size(); i++) {
            answer.setAnswerDescribe(answerDescribe);
            for (answerOrder = 0; answerOrder < i; answerOrder++) {
                answer.setAnswerOrder(answerOrder);
                answer.setQuestId(questId);
            }
            list.add(answer);

            System.out.println(list);
        }

        return "answerList";
    }

    @RequestMapping("/answerList")
    public List<ModuleTestAnswer> loadAswers(Model model,int answerId) {
        ModuleTestAnswer answer = answerService.findByAnswerId(answerId);
        model.addAttribute("answer",answer);
        return answerService.loadAnswer();
    }

//实验报告增加
//    @GetMapping("/addReport")
//    public String addReport() {
//        return "/addReport";
//    }
//
//    @PostMapping("/addReport")
//    public String addReport(String reportDescribe,String reportType,float reportScore,int reportOrder,int mId) {
//        Report report = new Report();
//        report.setReportDescribe(reportDescribe);
//        report.setReportType(reportType);
//        report.setReportScore(reportScore);
//        report.setReportOrder(reportOrder);
//        report.setmId(mId);
//        reportService.addReport(report);
//        return "/ReportList";
//    }
//
//    //删除实验
//    @RequestMapping("/deleteReport/{reportId}")
//    public String deleteReport(@PathVariable("reportId") int reportId) {
//        reportService.deleteReport(reportId);
//        return "/questList";
//    }
//
//    /**
//     * 修改实验信息
//     *
//     * @param reportId
//     * @param model
//     * @return
//     */
//    @GetMapping("/updateReport/{reportId}")
//    public String updateReport(@PathVariable("reportId") int reportId, Model model) {
//        Report report = reportService.updateReport(reportId);
//        model.addAttribute("report",report);
//        return "/updateQuest";
//    }
//
//    @PostMapping("/updateReport/{reportId}")
//    public String updateReport(@PathVariable("reportId") int reportId, Report report) {
//        Report r = reportService.updateReport(reportId);
//
//        r.setReportDescribe(report.getReportDescribe());
//        r.setReportType(report.getReportType());
//        r.setReportScore(report.getReportScore());
//        r.setReportOrder(report.getReportOrder());
//        r.setmId(report.getmId());
//        reportService.addReport(r);
//        return "redirect:/questList";
//    }
//
//    //    查询所有实验
//    @RequestMapping("/loadReport")
//    public List<Report> loadReport(Model model) {
//        Report reports = new Report();
//        model.addAttribute("reports",reports);
//        return reportService.loadReport();
//    }
//
//
//    //学生填写实验报告
//    @GetMapping("/addReportAnswer")
//    public String addReportAnswer() {
//        return "/addReport";
//    }
//
//    @PostMapping("/addReportAnswer")
//    public String addReportAnswer(String reportDescribe,String reportType,float reportScore,int reportOrder,int mId) {
//        Report report = new Report();
//        report.setReportDescribe(reportDescribe);
//        report.setReportType(reportType);
//        report.setReportScore(reportScore);
//        report.setReportOrder(reportOrder);
//        report.setmId(mId);
//        reportService.addReport(report);
//        return "/ReportList";
//    }
//
//    //删除实验
//    @RequestMapping("/deleteReport/{reportId}")
//    public String deleteReport(@PathVariable("reportId") int reportId) {
//        reportService.deleteReport(reportId);
//        return "/questList";
//    }
//
//    /**
//     * 修改实验信息
//     *
//     * @param reportId
//     * @param model
//     * @return
//     */
//    @GetMapping("/updateReport/{reportId}")
//    public String updateReport(@PathVariable("reportId") int reportId, Model model) {
//        Report report = reportService.updateReport(reportId);
//        model.addAttribute("report",report);
//        return "/updateQuest";
//    }
//
//    @PostMapping("/updateReport/{reportId}")
//    public String updateReport(@PathVariable("reportId") int reportId, Report report) {
//        Report r = reportService.updateReport(reportId);
//
//        r.setReportDescribe(report.getReportDescribe());
//        r.setReportType(report.getReportType());
//        r.setReportScore(report.getReportScore());
//        r.setReportOrder(report.getReportOrder());
//        r.setmId(report.getmId());
//        reportService.addReport(r);
//        return "redirect:/questList";
//    }
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