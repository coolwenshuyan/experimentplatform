package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.DTO.StudentLastTestScoreDTO;
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
 * 期末理论测试成绩管理
 * 把所有人的期末成绩列到一起
 * @author 王雨来
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping(value = "/lastTestScoreManage")
public class LastTestScoreController {

    @Autowired
    public StudentRepository studentRepository;
    @Autowired
    public KaoheModelRepository kaoheModelRepository;
    @Autowired
    public StudentService studentService;
    @Autowired
    public ClazzService classService;

    /**
     * 所有列表
     * @param model 传值
     * @param select_orderId 搜索值
     * @param pageNum 分页
     * @return 页面
     */
    @GetMapping(value = "/list")
    public String loadAllScore(Model model,
                               @RequestParam(required = true, defaultValue = "")String select_orderId ,
                               @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {

        Page<Student> c = studentService.findStudentPageAndXuehao(pageNum, select_orderId);
//        //搜索
        System.out.println("c>>>>>>"+c.getSize());
        model.addAttribute("allStu",c);
        model.addAttribute("selectOrderId",select_orderId);

        //查询当期班级列表
        //班级列表
//        List<ClassModel> classList = classService.findAllClass();
        List<ClassModel> classList = classService.findCurrentClass();
        model.addAttribute("classList",classList);

        //学生成绩DTO列表
        Page<StudentLastTestScoreDTO> a = studentService.listStudentLastTestAnswerDTO(pageNum);
        System.out.println(">>>>>>>>>>>>>>>>>"+a);
        model.addAttribute("allInfo",a);

        return "kaohe/lastTestScore";
    }

    /**
     * 分班级查看成绩
     * @param model 传值
     * @param classId 班级id
     * @param select_orderId 搜索值
     * @param pageNum 分页
     * @return 页面
     */
    @GetMapping(value = "/{classId}/list")
    public String loadOneClassScore(Model model,
                                    @PathVariable int classId,
                                    @RequestParam(required = true, defaultValue = "")String select_orderId ,
                                    @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {
        Page<Student> c = studentService.pageStudentByClassId(pageNum,classId);
        //搜索
        model.addAttribute("allStu",c);
        model.addAttribute("selectOrderId",select_orderId);

        //查询班级列表
        List<ClassModel> classList = classService.findCurrentClass();
        model.addAttribute("classList",classList);

        //学生成绩DTO列表
        Page<StudentLastTestScoreDTO> a = studentService.listStudentLastTestScoreDTOBYClassID(pageNum,classId);
        System.out.println(a);
        model.addAttribute("allInfo",a);

        return "kaohe/lastTestScore";
    }
//    @GetMapping(value = "/{classId}/list")
//    public String loadOneClassModel(Model model,
//                               @PathVariable int classId,
//                               @RequestParam(required = true, defaultValue = "")String select_orderId ,
//                               @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {
//
////        Page<Student> c = studentService.findAll(pageNum);
//        Page<Student> c = studentService.pageStudentByClassId(pageNum,classId);
//
//        System.out.println(">>>>>>>>>>>>>>>>>>c"+c);
//        model.addAttribute("allStu",c);
//        model.addAttribute("selectOrderId",select_orderId);
//
//        List<ClassModel> classList = classService.findAllClass();
//        model.addAttribute("classList",classList);
//
//        List<StudentTestScoreDTO> a = studentRepository.listStudentMTestAnswerDTO();
//
//        System.out.println(a);
//        long modleNum = kaoheModelRepository.count();
//        model.addAttribute("allInfo",a);
//        model.addAttribute("num",modleNum);
//        List<Integer> list = new ArrayList<Integer>();
//        for(int i=1;i<=modleNum;i++){
//            list.add(i);
//        }
//        System.out.println(list);
//        model.addAttribute("numList",list);
//        return "kaohe/score_manage";
//    }


}
