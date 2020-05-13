package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.QuestionDao;
import com.coolwen.experimentplatform.model.Question;
import com.coolwen.experimentplatform.model.Reply;
import com.coolwen.experimentplatform.service.QuestionService;
import com.coolwen.experimentplatform.service.QuestionServiceImpl;
import com.coolwen.experimentplatform.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "question")
public class QuesstionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionServiceImpl questionServiceImpl;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private ReplyService replyService;

    //用户提问点击提交
    @GetMapping(value = "/add")
    public String QuestionAdd() {
        return "question_reply/add";
    }

    //完成添加操作
    @PostMapping(value = "/add")
    public String add(Question question, String uploadfile) {
        question.setSid(2);
//        question.setContent("alg");
        question.setDic_datetime(new Date());
//        Effect e = new Effect();
//        e.setEffect_name(effect.getEffect_name());
//        e.setDic_datetime(effect.getDic_datetime());
//        e.setDic_num(effect.getDic_num());
//        e.setEffect_content(effect.getEffect_content());
//        e.setEffect_imgurl(effect.getEffect_imgurl());
        questionService.add(question);
        return "redirect:/question/add";//list
    }

    //查出来
    @GetMapping(value = "/list")
    public String QuestionList(Model model, @RequestParam(defaultValue = "0", required = true, value = "pageNum") Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<Question> page = questionDao.findAll(pageable);
        model.addAttribute("questionPageInfo", page);
        return "shouye/dayiManage";
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

    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id) {
        questionService.delete(id);
        return "redirect:/question/list";
    }

    //后台管理进入答疑室
    @GetMapping(value = "index")
    public String index() {
        return "question_reply/index";
    }

    //进入查看页面
    @GetMapping(value = "/{id}/seesee")
    public String seesee(@PathVariable int id,Model model) {
        Question question = questionService.findById(id);
        model.addAttribute("question", question);
        List<Reply> replies = replyService.findByreplycontent(id);
        model.addAttribute("replies",replies);
        return "shouye/dayiMore";
    }

}
