package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.kit.ShiroKit;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.StudentListDTO;
import com.coolwen.experimentplatform.service.*;
import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.DTO.StudentVo;
import com.coolwen.experimentplatform.service.ClazzService;
import com.coolwen.experimentplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

/**
 *@Description 后台管理系统 学生管理页面
 *@Author 张健银
 *@Version 1.0
 *@Date 2020/5/31
 */

@Controller
@RequestMapping("/studentManage")
public class StudentController {
    @Autowired
    StudentService studentservice;
    @Autowired
    ClazzService clazzService;
    @Autowired
    KaoheModelService kaoheModelService;
    @Autowired
    KaoHeModelScoreService kaoHeModelScoreService;
    @Autowired
    TotalScoreCurrentService totalScoreCurrentService;
    @Autowired
    ModuleTestAnswerStuService moduleTestAnswerStuService;
    @Autowired
    ReportAnswerService reportAnswerService;
    @Autowired
    TotalScorePassService totalScorePassService;
    @Autowired
    ExpModelService expModelService;

    //查询学生列表
    @GetMapping("/list")
    public String studentList(Model model, @RequestParam(value = "pageNum",defaultValue = "0")int pageNum){
        Page<StudentVo> list = studentservice.findStudentsByStuCheckstate(pageNum);
        model.addAttribute("studentList",list);
        return "student/student_list";
    }


    //搜索学生学号
    @GetMapping("/viewStudent")
    public String viewStudent(@RequestParam("stu_xuehao")String stu_xuehao,Model model){
        StudentVo student = studentservice.findStudentsByStuXuehao(stu_xuehao);
        if (student == null){
            return "redirect:/student/list";
        }
        model.addAttribute("stu",student);
        return "student/student_view";
    }

    //执行学生删除
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        //删除模块回答
        moduleTestAnswerStuService.deleteModuleTestAnswerStuByStuId(id);
        //删除报告回答
        reportAnswerService.deleteReportAnswerByStuId(id);
        //删除考核模块成绩
        kaoHeModelScoreService.deleteKaoheModuleScoreByStuId(id);
        Student student = studentservice.findStudentById(id);
        //判断学生班级情况进行当期往期操作
        if(student.getClassId() != 0){
            ClassModel classModel = clazzService.findById(student.getClassId());
            if(classModel.getClassIscurrent() == false){
                //当期
                totalScoreCurrentService.deleteTotalScoreCurrentByStuId(id);
            }else {
                totalScorePassService.delteTotalScorePassByStuId(id);
            }
        }
        studentservice.deleteStudentById(id);
        return "redirect:/studentManage/list";
    }

    //进入学生编辑
    @GetMapping("/editStudent/{id}")
    public String toeditStudent(@PathVariable("id")int id,Model model){
        Student s = studentservice.findStudentById(id);
        StudentListDTO student = null;
        if(s.getClassId() == 0){
            student = new StudentListDTO();
            student.setClassid(0);
            student.setClassName(null);
            student.setId(s.getId());
            student.setStuCheckstate(s.isStuCheckstate());
            student.setStuIsinschool(s.isStuIsinschool());
            student.setStuMobile(s.getStuMobile());
            student.setStuName(s.getStuName());
            student.setStuPassword(s.getStuPassword());
            student.setStuUname(s.getStuUname());
            student.setStuXuehao(s.getStuXuehao());
        }else {
            student = studentservice.findStudentDTOById(id);
        }
        List<ClassModel> classModels = clazzService.findAllClass();
        model.addAttribute("stu",student);
        model.addAttribute("class",classModels);
        return "student/student_alter";

    }
    //学生编辑操作
    @PostMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") int id,
                              String stu_uname,
                              String stu_name,
                              String stu_xuehao,
                              int classid,
                              Boolean stuIsinschool,
                              String stu_password
                              )
    {
        Student student = studentservice.findStudentById(id);

        student.setStuUname(stu_uname);
        student.setStuName(stu_name);
        student.setStuXuehao(stu_xuehao);
        student.setStuIsinschool(stuIsinschool);
        student.setStuPassword(ShiroKit.md5(stu_password,stu_uname));
        student.setClassId(classid);
        studentservice.saveStudent(student);
        return "redirect:/studentManage/list";
    }

    //返回待审核学生列表
    @GetMapping("/toBeReviewd")
    public String toBeReviewed(@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum,Model model){
        model.addAttribute("waitStudent",studentservice.findToBeReviewedStudent(pageNum));
        return "student/student_examine";
    }

    //通过审核操作
    @GetMapping("/passReviewd/{id}")
    public String passReview(@PathVariable("id") int id){
        Student student = studentservice.findStudentById(id);
        student.setStuCheckstate(true);
        studentservice.saveStudent(student);
        return "redirect:/studentManage/toBeReviewd";
    }
    //驳回学生审核
    @GetMapping("/deleteReviewd/{id}")
    public String deleteReviewd(@PathVariable("id") int id){
        studentservice.deleteStudentById(id);
        return "redirect:/studentManage/toBeReviewd";
    }
    //搜索审核学生列表
    @GetMapping("/viewReviewd")
    public String viewReviewd(@RequestParam("stu_xuehao")String stu_xuehao,Model model){
        Student student = studentservice.findStudentByStuXuehao(stu_xuehao);
        model.addAttribute("student",student);
        return "student/student_examine_view";
    }

    //返回班级列表
    @GetMapping("/classManage")
    public String classList(Model model,@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        model.addAttribute("classMsg",clazzService.findClassList(pageNum));
        return "student/class_administer";
    }
    //进入班级编辑
    @GetMapping("/toEditClass/{id}")
    public String editClass(@PathVariable("id")int id,Model model){
        model.addAttribute("class",clazzService.findById(id));
        return "/student/class_alter";
    }
    //进入班级添加
    @GetMapping("/toAddClass")
    public String toAddClass(){
        return "student/class_add";
    }


    //添加班级操作
    @PostMapping("/addClass")
    public String addClass(
                            String class_collage,
                            String class_major,
                            String class_grade,
                            String class_name,
                            String class_teacher
    )
    {
        ClassModel clazz = new ClassModel();
        clazz.setClassCollage(class_collage);
        clazz.setClassMajor(class_major);
        clazz.setClassGrade(class_grade);
        clazz.setClassName(class_name);
        clazz.setClassTeacher(class_teacher);
        clazzService.saveClazz(clazz);
        return "redirect:/studentManage/classManage";
    }



    //修改班级
    @PostMapping("/editClass/{id}")
    public String editClass(@PathVariable("id") int id,
                            String class_collage,
                            String class_major,
                            String class_grade,
                            String class_name,
                            String class_teacher,
                            Boolean classIscurrent
    )
    {
        ClassModel clazz = clazzService.findById(id);
        clazz.setClassCollage(class_collage);
        clazz.setClassMajor(class_major);
        clazz.setClassGrade(class_grade);
        clazz.setClassName(class_name);
        clazz.setClassTeacher(class_teacher);
        clazz.setClassIscurrent(classIscurrent);
        clazzService.saveClazz(clazz);
        //如果进行班级设置往期，则进行成绩固化
        if(classIscurrent == true){
            TotalScorePass totalScorePass = null;
            //初始化固化成绩信息
            String kaoheModuleName = "";
            String kaohe_mtestscore = "";
            String kaohe_mreportscore = "";
            String kaohe_mtestscore_baifengbi = "";
            String kaohe_mreportscore_baifengbi = "";
            String kaohe_mscale = "";
            float test_baifenbi = 0;
            float kaohe_baifenbi = 0;
            List<KaoheModel> kaoheModelList = kaoheModelService.findAll();
            //获得考核项目名字
            for (KaoheModel k : kaoheModelList){
                ExpModel expModel = expModelService.findExpModelsByKaoheMid(k.getM_id());
                kaoheModuleName += expModel.getM_name()+";";
                test_baifenbi = k.getTest_baifenbi();
                kaohe_baifenbi = k.getKaohe_baifenbi();
            }
            List<Student> studentList = studentservice.findStudentByClassId(id);
            for(Student s : studentList){
                for(KaoheModel k : kaoheModelList){
                    //获取该班级下学生每个考核模块信息
                    KaoHeModelScore kaoHeModelScore = kaoHeModelScoreService.findKaoHeModelScoreByStuIdAndId(s.getId(),k.getId());
                    //进行成绩固化临时存储
                    kaohe_mtestscore += kaoHeModelScore.getmTestScore()+";";
                    kaohe_mreportscore += kaoHeModelScore.getmReportScore()+";";
                    kaohe_mtestscore_baifengbi += k.getM_test_baifenbi()+";";
                    kaohe_mreportscore_baifengbi += k.getM_report_baifenbi()+";";
                    kaohe_mscale += k.getM_scale()+";";
                }
                TotalScoreCurrent totalScoreCurrent = totalScoreCurrentService.findTotalScoreCurrentByStuId(s.getId());
                //进行成绩固化操作
                totalScorePass = new TotalScorePass();
                totalScorePass.setStuId(s.getId());
                totalScorePass.setKaoheName(String.valueOf(kaoheModelList.size()));
                totalScorePass.setKaoheName(kaoheModuleName);
                totalScorePass.setKaoheMtestscore(kaohe_mtestscore);
                totalScorePass.setKaoheMreportscore(kaohe_mreportscore);
                totalScorePass.setKaoheMtestscoreBaifengbi(kaohe_mtestscore_baifengbi);
                totalScorePass.setKaoheMreportscoreBaifengbi(kaohe_mreportscore_baifengbi);
                totalScorePass.setKaoheMscale(kaohe_mscale);
                totalScorePass.setmTotalScore(totalScoreCurrent.getmTotalScore());
                totalScorePass.setTestScore(totalScoreCurrent.getTestScore());
                totalScorePass.setTestBaifenbi(test_baifenbi);
                totalScorePass.setKaoheBaifenbi(kaohe_baifenbi);
                totalScorePass.setTotalScore(totalScoreCurrent.getTotalScore());
                totalScorePass.setFinalDatetime(new Date());
                totalScorePassService.save(totalScorePass);
                //删除学生模块回答，报告回答，考核成绩表，以及当期总评成绩表
                moduleTestAnswerStuService.deleteModuleTestAnswerStuByStuId(s.getId());
                reportAnswerService.deleteReportAnswerByStuId(s.getId());
                kaoHeModelScoreService.deleteKaoheModuleScoreByStuId(s.getId());
                totalScoreCurrentService.deleteTotalScoreCurrentByStuId(s.getId());
            }

        }
        return "redirect:/studentManage/classManage";
    }


    //删除班级操作
    @GetMapping("/deleteClass/{id}")
    public String deleteClass(@PathVariable("id")int id){
        ClassModel classModel = clazzService.findById(id);
        List<Student> studentList = studentservice.findStudentByClassId(id);
        //根据班级状态对所属学生进行当期或往期成绩删除
        if(classModel.getClassIscurrent() == false){
            for(Student student : studentList){
                totalScoreCurrentService.deleteTotalScoreCurrentByStuId(student.getId());
            }
        }else {
            for(Student student : studentList){
                totalScorePassService.delteTotalScorePassByStuId(student.getId());
            }
        }
        //将学生班级重置
        clazzService.deleteClazz(id);
        for(Student s : studentList){
            s.setClassId(0);
            studentservice.saveStudent(s);
        }
        return "redirect:/studentManage/classManage";
    }

    //班级页面管理学生
    @GetMapping("/addStudent/{id}")
    public String toaddStudent(@PathVariable("id")int id,Model model){
        model.addAttribute("student",studentservice.findStudentByClassId(id));
        model.addAttribute("classId",id);
        return "student/class_add_student";
    }
    //为班级进行添加学生操作
    @PostMapping("/viewAddStudent/{id}")
    public String addStudent(@RequestParam("stu_xuehao")String xuehao, @PathVariable("id") int id){
        Student student = studentservice.findclassStudentByStuXuehao(xuehao);//分班的学生必须是审核过了
        student.setClassId(id);
        List<KaoheModel> kaoheModels = kaoheModelService.findAll();
        KaoHeModelScore kaoHeModelScore = null;
        //分班后进行学生考核成绩表生成操作
        if(!kaoheModels.isEmpty() && kaoheModels != null){
            for(KaoheModel km : kaoheModels){
                kaoHeModelScore = new KaoHeModelScore();
                kaoHeModelScore.settKaohemodleId(km.getId());
                kaoHeModelScore.setStuId(student.getId());
                kaoHeModelScore.setmOrder(km.getM_order());
                kaoHeModelScore.setmScale(km.getM_scale());
                kaoHeModelScoreService.add(kaoHeModelScore);
            }
        }
        //生成当期总评成绩表
        TotalScoreCurrent totalScoreCurrent = new TotalScoreCurrent();
        totalScoreCurrent.setStuId(student.getId());
        int kaoheNum = kaoheModelService.findKaoheNum();
        totalScoreCurrent.setKaoheNum(kaoheNum);
        totalScoreCurrentService.add(totalScoreCurrent);
        studentservice.saveStudent(student);
        return "redirect:/studentManage/addStudent/"+id;
    }
    //班级学生移除操作
    @GetMapping("/deleteStuClass/{id}")
    public String deleteStuClass(@PathVariable("id") int id){
        totalScoreCurrentService.deleteTotalScoreCurrentByStuId(id);
        List<KaoHeModelScore> kaoHeModelScores = kaoHeModelScoreService.findKaoheModuleScoreByStuId(id);
        //删除学生考核信息
        kaoHeModelScoreService.deleteAllKaohe(kaoHeModelScores);
        Student student = studentservice.findStudentById(id);
        int preClassId = student.getClassId();
        student.setClassId(0);
        studentservice.saveStudent(student);
        return "redirect:/studentManage/addStudent/"+preClassId;
    }
    //搜索班级操作
    @GetMapping("/viewClass")
    public String viewClass(@RequestParam("class_name") String class_name,Model model){
        ClassModel clazz = studentservice.findClazzByClassName(class_name);
        model.addAttribute("class",clazz);
        return "student/class_view";
    }



}















