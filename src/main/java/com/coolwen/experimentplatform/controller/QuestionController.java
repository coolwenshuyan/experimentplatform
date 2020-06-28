package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.*;
import com.coolwen.experimentplatform.exception.UserException;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.QuestionStudentDto;
import com.coolwen.experimentplatform.service.QuestionService;
import com.coolwen.experimentplatform.service.QuestionServiceImpl;
import com.coolwen.experimentplatform.service.ReplyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
/**
 *
 *  @author yellow
 */
@Controller
@RequestMapping(value = "question")
public class QuestionController {

//    注入
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    StudentRepository studentRepository;

//    //用户提问点击进入提交页
//    @GetMapping(value = "/add")
//    public String QuestionAdd() {
//        return "question_reply/add";
//    }

    //学生完成添加提交问题操作
    @PostMapping(value = "/add")
    public String add(Question question, Session session) {
//        从seesion拿到student的内容
//        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        Student student = (Student) session.getAttribute("student");

        //暂时做了修改
        if (student == null){
            throw new UserException("请先登录后再来提问!");
        }

//        插入数据到数据库
        question.setSid(student.getId());
        question.setIsreply(false);
        question.setDic_datetime(new Date());
        questionService.add(question);
        return "redirect:/question/list1";//list
    }

    //老师端看到question列表，查出来
    @GetMapping(value = "/list")
    public String QuestionList(Model model, @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
//        分页查询，每页最多五条数据
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<QuestionStudentDto> page = questionService.findAndUname(pageable);
        model.addAttribute("questionPageInfo", page);
        return "shouye/dayiManage";
    }

    //学生用户端看到question列表，查出来
    @GetMapping(value = "/list1")
    public String QuestionList1(Model model, @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
//        分页查询，每页最多五条数据
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<QuestionStudentDto> page = questionService.findAndUname(pageable);
        model.addAttribute("questionPageInfo", page);
        return "home_page/question";
    }

//    //进入修改界面
//    @GetMapping(value = "/{id}/update")
//    public String update(@PathVariable int id, Model model) {
//        Reply reply = replyService.findById(id);
//        model.addAttribute("reply",reply);
////        Question question = questionService.findById(id);
////        model.addAttribute("question", question);
//        return "question_reply/update";
//    }
//
//    //完成修改操作
//    @PostMapping(value = "/{id}/update")
//    public String update(@PathVariable int id, Question question) {
//        question.setId(id);
//        question.setSid(99);
////        name
//        question.setContent("adsadsad");
//        question.setDic_datetime(new Date());
//        questionService.add(question);
//        System.out.println("修改成功");
//        return "redirect:/questionORreply/{id}/seesee";
//    }

    //删除问题及所有回复
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id) {
//        通过id删除所有该问题的回复
        replyService.deleteByQid(id);
//        通过id删除该问题
        questionService.delete(id);
        return "redirect:/question/list";
    }

//    //后台管理进入答疑室
//    @GetMapping(value = "index")
//    public String index() {
//        return "question_reply/index";
//    }


    //老师进入查看页
    @GetMapping(value = "/{id}/dayiMore")
    public String seesee(@PathVariable int id, Model model) {
//        查到该问题
        Question question = questionService.findById(id);
        model.addAttribute("question", question);
//        查到所有回复
        List<Reply> replies = replyService.findByreplycontent(id);
        model.addAttribute("replies", replies);
//        查出学生的名字
        int a = question.getSid();
        String studentName = studentRepository.findStudentname(a);
        model.addAttribute("studentName", studentName);
        return "shouye/dayiMore";
    }

    //学生进入查看页
    @GetMapping(value = "/detaill/{id}")
    public String seesee1(@PathVariable int id, Model model) {
//        查到该问题
        Question question = questionService.findById(id);
        model.addAttribute("question", question);
//        查到所有回复
        List<Reply> replies = replyService.findByreplycontent(id);
        model.addAttribute("replies", replies);
//        查出学生的名字
        int a = question.getSid();
        String studentName = studentRepository.findStudentname(a);
        model.addAttribute("studentName", studentName);
        return "home_page/detaill";
    }

}
