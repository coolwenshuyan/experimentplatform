package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.service.ClazzService;
import com.coolwen.experimentplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生模块报告成绩管理
 * 列出所有学生的所有的模块的报告成绩
 * @author 王雨来
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping(value = "/reportScoreManage")
public class ModleTestReportController {

    @Autowired
    public StudentRepository studentRepository;
    @Autowired
    public KaoheModelRepository kaoheModelRepository;
    @Autowired
    public StudentService studentService;
    @Autowired
    public ClazzService classService;


    /**
     * 列出所有学生的所有的模块的报告成绩
     * @param select_orderId 搜索值
     * @param pageNum 分页
     * @return 页面
     */
    @GetMapping(value = "/list")
    public String loadAllModel(Model model,
                               @RequestParam(required = true, defaultValue = "")String select_orderId ,
                               @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {

//        Page<Student> c = studentService.findAll(pageNum);
        //获得所有学生
        Page<Student> c = studentService.findStudentPageAndXuehao(pageNum, select_orderId);
        System.out.println(">>>>>>>>>>>>>>>>>>c"+c);
        model.addAttribute("allStu",c);
        model.addAttribute("selectOrderId",select_orderId);

        //获得所有班级
        List<ClassModel> classList = classService.findAllClass();
        model.addAttribute("classList",classList);

        //获得所有学生成绩DTO
        List<StudentTestScoreDTO> a = studentRepository.listStudentMReportAnswerDTO();
        System.out.println(a);

        //统计所以考核模块的个数,生成自增列表,以便thymeleaf生成表头
        long modleNum = kaoheModelRepository.count();
        model.addAttribute("allInfo",a);
        model.addAttribute("num",modleNum);
        List<Integer> list = new ArrayList<Integer>();
        for(int i=1;i<=modleNum;i++){
            list.add(i);
        }
        System.out.println(list);
        model.addAttribute("numList",list);
        return "kaohe/score2_manage";
    }

    /**
     * 列出所有学生的所有的模块的报告成绩
     * @param classId 班级id 用来筛选
     * @param select_orderId 搜索值
     * @param pageNum 分页
     * @return 页面
     */
    @GetMapping(value = "/{classId}/list")
    public String loadOneClassModel(Model model,
                               @PathVariable int classId,
                               @RequestParam(required = true, defaultValue = "")String select_orderId ,
                               @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {

//        Page<Student> c = studentService.findAll(pageNum);
        //除了班级筛选,其它和上面的一模一样
        Page<Student> c = studentService.pageStudentByClassId(pageNum,classId);

        System.out.println(">>>>>>>>>>>>>>>>>>c"+c);
        model.addAttribute("allStu",c);
        model.addAttribute("selectOrderId",select_orderId);

        List<ClassModel> classList = classService.findAllClass();
        model.addAttribute("classList",classList);

        List<StudentTestScoreDTO> a = studentRepository.listStudentMReportAnswerDTO();

        System.out.println(a);
        long modleNum = kaoheModelRepository.count();
        model.addAttribute("allInfo",a);
        model.addAttribute("num",modleNum);
        List<Integer> list = new ArrayList<Integer>();
        for(int i=1;i<=modleNum;i++){
            list.add(i);
        }
        System.out.println(list);
        model.addAttribute("numList",list);
        return "kaohe/score2_manage";
    }


}
