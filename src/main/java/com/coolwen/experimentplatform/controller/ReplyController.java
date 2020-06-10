package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.ReplyRepository;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.service.QuestionService;
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
/**
 *
 *  @author yellow
 */
@Controller
@RequestMapping(value = "reply")
public class ReplyController {

//    注入
    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private QuestionService questionService;

    //用户提问点击提交
//    @GetMapping(value = "/add1")
//    public String ReplyAdd(@PathVariable int id) {
//        return "question_reply/seesee";
//    }

    //老师完成添加回复操作
    @PostMapping(value = "/{id}/add1")
    public String add(@PathVariable int id, Reply reply, String uploadfile, Session session) {

//        回复的问题地存为qid
        reply.setQid(id);
        System.out.println("插入的回复保存为：" + id);

//        seesion获得老师信息
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();

//        插入回复
        reply.setReply_pname(admin.getUname());//获得并存入回复名字
        reply.setDic_datetime(new Date());
        replyService.add(reply);
        Question question = questionService.findById(id);
//        表示已回复
        question.setIsreply(true);
        questionService.add(question);
        return "redirect:/question/" + id + "/dayiMore";//list
    }

    //学生回复并操作
    @PostMapping(value = "/add2/{id}")
    public String add1(@PathVariable int id, Reply reply, Session session) {

//        回复的问题地存为qid
        reply.setQid(id);
        System.out.println("插入的回复保存为：" + id);

//        seesion获得学生信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();

//        插入回复
        reply.setReply_pname(student.getStuUname());//获得并存入回复名字
        reply.setDic_datetime(new Date());
        replyService.add(reply);
        Question question = questionService.findById(id);
//      表示老师未回复
        question.setIsreply(false);
        questionService.add(question);
        return "redirect:/question/"  + "detaill/"+ id;//list
    }


//    //查出来
//    @GetMapping(value = "/list")
//    public String ReplyList(Model model, @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
//        Pageable pageable = PageRequest.of(pageNum, 5);
//        Page<Reply> page = replyRepository.findAll(pageable);
//        model.addAttribute("replyPageInfo", page);
//        return "question_reply/reply";
//    }

//    //进入修改界面
//    @GetMapping(value = "/{id}/update")
//    public String update(@PathVariable int id, Model model) {
//        Reply reply = replyService.findById(id);
//        model.addAttribute("reply",reply);
//        return "question_reply/update";
//    }

    //老师完成修改操作
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id, Reply reply) {
//        先查出内容
        Reply replyupdate = replyService.findById(id);
//        重新写入内容
        replyupdate.setContent(reply.getContent());
        replyService.add(replyupdate);
        System.out.println("修改成功");
        return "redirect:/question/" + replyupdate.getQid() + "/dayiMore";
    }

    //删除
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id) {
        Reply reply = replyService.findById(id);
        int mid = reply.getQid();
        replyService.delete(id);
        return "redirect:/question/" + mid + "/dayiMore";
    }
}
