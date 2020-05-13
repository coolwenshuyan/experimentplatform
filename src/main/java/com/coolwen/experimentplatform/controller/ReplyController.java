package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.ReplyDao;
import com.coolwen.experimentplatform.model.Reply;
import com.coolwen.experimentplatform.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(value = "reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyDao replyDao;

    //用户提问点击提交
//    @GetMapping(value = "/add1")
//    public String ReplyAdd(@PathVariable int id) {
//        return "question_reply/seesee";
//    }

    //完成添加操作
    @PostMapping(value = "/{id}/add1")
    public String add(@PathVariable int id,Reply reply, String uploadfile) {
        reply.setQid(id);
        System.out.println("adhasjkhafghjfga"+id);
        reply.setReply_pname("yellow");
//        question.setContent("alg");
        reply.setDic_datetime(new Date());
        replyService.add(reply);
        System.out.println("adhasjkhafghjfga" + reply);
        return "redirect:/question/"+id+"/seesee";//list
    }

    //查出来
    @GetMapping(value = "/list")
    public String ReplyList(Model model, @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<Reply> page = replyDao.findAll(pageable);
        model.addAttribute("replyPageInfo", page);
        return "question_reply/reply";
    }

//    //进入修改界面
//    @GetMapping(value = "/{id}/update")
//    public String update(@PathVariable int id, Model model) {
//        Reply reply = replyService.findById(id);
//        model.addAttribute("reply",reply);
//        return "question_reply/update";
//    }

    //完成修改操作
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id, Reply reply) {
        Reply replyupdate = replyService.findById(id);
        replyupdate.setContent(reply.getContent());
        replyService.add(replyupdate);
        System.out.println("修改成功");
        return "redirect:/question/"+replyupdate.getQid()+"/seesee";
    }

    //删除
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id) {
        Reply reply = replyService.findById(id);
        int mid = reply.getQid();
        replyService.delete(id);
        return "redirect:/question/"+mid+"/seesee";
    }
}
