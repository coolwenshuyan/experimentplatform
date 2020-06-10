/**
 * 文件名：LearningeffectController.java
 * 修改人：xxxx
 * 修改时间：
 * 修改内容：新增
 */

package com.coolwen.experimentplatform.controller;
import com.coolwen.experimentplatform.controller.HomepagesettingController.FileUploadController;
import com.coolwen.experimentplatform.dao.TeacherRepository;
import com.coolwen.experimentplatform.model.Teacher;
import com.coolwen.experimentplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

/**
*@Description 后台管理系统-->首页-->师资队伍页面
*@Author 王宇
*@Version 1.0
*@Date 2020/5/30 11:06
*/
@Controller
@RequestMapping(value = "/teachers")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherService teacherService;

    FileUploadController fileUploadController =new FileUploadController();  //上传文件

    /**
     * 前端师资队伍页面
     */
    @GetMapping(value = "/frontList")
    public String TeacherFrontList(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,100);
        Page<Teacher> page = teacherRepository.findAll(pageable);
        model.addAttribute("teacherPageInfo",page);
        return "home_page/teachers";
    }


    /**
     * 后台管理系统，首页-->师资队伍列表查询
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/list")
    public String TeacherList(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,3);
        Page<Teacher> page = teacherRepository.findAll(pageable);
        model.addAttribute("teacherPageInfo",page);
        return "shouye/teacher_change";
    }
    /**
     * 后台管理系统 师资队伍单条删除
     */
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        teacherService.delete(id);
        return "redirect:/teachers/list";
    }
    /**
     * 跳转到师资队伍添加页面
     */
    @GetMapping(value = "/add")
    public String TeacherAdd(){
        return "shouye/teacher_add";
    }


    /**
     * 完成师资添加
     * @param teacher 前端返回的参数（person_name，intro）
     * @param file  前端传回的图片
     * @param req
     * @return 重定向到师资队伍列表
     */
    @PostMapping(value = "/add")
    public String add(Teacher teacher, MultipartFile file, HttpServletRequest req){
        //储存图片，并将图片路径储存到数据库
        String url = fileUploadController.upload(file,req);
        teacher.setImage_url(url);
        teacherService.add(teacher);
        return "redirect:/teachers/list";
    }
    //进入修改界面
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id, Model model){
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher",teacher);
        return "shouye/teacher_updata";
    }

    //完成修改操作
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id, Teacher teacher,MultipartFile file, HttpServletRequest req){
        teacher.setId(id);
        //如果前端上传了图片，则更新图片储存路径，如果没有，则为之前的图片路径
        String url = fileUploadController.upload(file,req);
        if (url !=null){
            teacher.setImage_url(url);
        }else {
            teacher.setImage_url(teacherService.findById(id).getImage_url());
        }
        teacherService.add(teacher);
        System.out.println("修改成功");
        return "redirect:/teachers/list";
    }

}
