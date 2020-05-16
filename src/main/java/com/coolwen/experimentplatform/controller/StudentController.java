package com.coolwen.experimentplatform.controller;

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
import java.util.List;

@Controller
@RequestMapping("/studentManage")
public class StudentController {
    @Autowired
    StudentService studentservice;
    @Autowired
    ClazzService clazzService;


    //学生列表
    @GetMapping("/list")
    public String studentList(Model model, @RequestParam(value = "pageNum",defaultValue = "0")int pageNum){
        Page<StudentVo> list = studentservice.findStudentsByStuCheckstate(pageNum);
        model.addAttribute("studentList",list);
        for (StudentVo s : list){
            System.out.println(s.getClassName());
        }
        return "student/student_list";
    }


    //搜索学号
    @GetMapping("/viewStudent")
    public String viewStudent(@RequestParam("stu_xuehao")String stu_xuehao,Model model){
        StudentVo student = studentservice.findStudentsByStuXuehao(stu_xuehao);
        if (student == null){
            return "redirect:/student/list";
        }
        model.addAttribute("stu",student);
        return "student/student_view";
    }

    //学生删除
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        studentservice.deleteStudentById(id);
        return "redirect:/student/list";
    }

    //学生编辑
    @GetMapping("/editStudent/{id}")
    public String toeditStudent(@PathVariable("id")int id,Model model){
        StudentVo student = studentservice.findStudentVoById(id);
        model.addAttribute("stu",student);
        return "student/student_alter";

    }

    @PostMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") int id,
                              String stu_uname,
                              String stu_name,
                              String stu_xuehao,
                              String className,
                              Boolean stuIsinschool,
                              String stu_password
                              )
    {
        ClassModel clazz = studentservice.findClazzByClassName(className);
        Student student = studentservice.findStudentById(id);
        if(clazz != null){
            student.setClassId(clazz.getClassId());
        }
        student.setStuUname(stu_uname);
        student.setStuName(stu_name);
        student.setStuXuehao(stu_xuehao);
        student.setStuIsinschool(stuIsinschool);
        student.setStuPassword(stu_password);
        studentservice.saveStudent(student);
        return "redirect:/student/list";
    }

    //待审核学生

    @GetMapping("/toBeReviewd")
    public String toBeReviewed(@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum,Model model){
        model.addAttribute("waitStudent",studentservice.findToBeReviewedStudent(pageNum));
        return "student/student_examine";
    }

    //通过审核
    @GetMapping("/passReviewd/{id}")
    public String passReview(@PathVariable("id") int id){
        Student student = studentservice.findStudentById(id);
        student.setStuCheckstate(true);
        studentservice.saveStudent(student);
        return "redirect:/studentManage/toBeReviewd";
    }

    @GetMapping("/deleteReviewd/{id}")
    public String deleteReviewd(@PathVariable("id") int id){
        studentservice.deleteStudentById(id);
        return "redirect:/studentManage/toBeReviewd";
    }

    @GetMapping("/viewReviewd")
    public String viewReviewd(@RequestParam("stu_xuehao")String stu_xuehao,Model model){
        Student student = studentservice.findStudentByStuXuehao(stu_xuehao);
        model.addAttribute("student",student);
        return "student/student_examine_view";
    }

    //班级列表

    @GetMapping("/classManage")
    public String classList(Model model,@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        model.addAttribute("classMsg",clazzService.findClassList(pageNum));
        return "student/class_administer";
    }

    @GetMapping("/toEditClass/{id}")
    public String editClass(@PathVariable("id")int id,Model model){
        model.addAttribute("class",clazzService.findById(id));
        return "/student/class_alter";
    }
    @GetMapping("/toAddClass")
    public String toAddClass(){
        return "student/class_add";
    }


    //添加班级
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
        return "redirect:/studentManage/classManage";
    }


    //删除班级
    @GetMapping("/deleteClass/{id}")
    public String deleteClass(@PathVariable("id")int id){
        clazzService.deleteClazz(id);
        List<Student> studentList = studentservice.findStudentByClassId(id);
        for(Student s : studentList){
            s.setClassId(0);
            studentservice.saveStudent(s);
        }
        return "redirect:/studentManage/classManage";
    }

    //管理学生
    @GetMapping("/addStudent/{id}")
    public String toaddStudent(@PathVariable("id")int id,Model model){
        model.addAttribute("student",studentservice.findStudentByClassId(id));
        model.addAttribute("classId",id);
        return "student/class_add_student";
    }

    @PostMapping("/viewAddStudent/{id}")
    public String addStudent(@RequestParam("stu_xuehao")String xuehao, @PathVariable("id") int id){
        Student student = studentservice.findStudentByStuXuehao(xuehao);//分班的学生必须是审核过了
        student.setClassId(id);
        studentservice.saveStudent(student);
        return "redirect:/studentManage/addStudent/"+id;
    }

    @GetMapping("/deleteStuClass/{id}")
    public String deleteStuClass(@PathVariable("id") int id){
        Student student = studentservice.findStudentById(id);
        int preClassId = student.getClassId();
        student.setClassId(0);
        studentservice.saveStudent(student);
        return "redirect:/studentManage/addStudent/"+preClassId;
    }

    @GetMapping("/viewClass")
    public String viewClass(@RequestParam("class_name") String class_name,Model model){
        ClassModel clazz = studentservice.findClazzByClassName(class_name);
        model.addAttribute("class",clazz);
        return "student/class_view";
    }



}















