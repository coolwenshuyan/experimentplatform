/**
 * 文件名：AdminController.java
 * 修改人：xxxx
 * 修改时间：
 * 修改内容：新增
 */

package com.coolwen.experimentplatform.controller;
import com.coolwen.experimentplatform.dao.AdminDao;
import com.coolwen.experimentplatform.kit.ShiroKit;
import com.coolwen.experimentplatform.model.Admin;
import com.coolwen.experimentplatform.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
*@Description 后台管理系统 教师账号管理页面
*@Author 冯博
*@Version 1.0
*@Date 2020/5/29 21:24
*/
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminDao adminDao;
    @Autowired
    AdminService adminService;

    //查询所有数据
    @GetMapping(value = "/list")
    public String AdminList(Model model, @RequestParam(defaultValue = "0", required=true, value = "pageNum")  Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<Admin> page = adminDao.findAll(pageable);
        model.addAttribute("AdminPageInfo", page);
        return "shouye/teacherID_list";
    }

    //点击添加教师账号，跳转到添加界面
    @GetMapping(value = "/add")
    public String AdminAdd(){
        return "shouye/teacherID_add";
    }

    //完成教师添加操作
    @PostMapping(value = "/add")
    public String add(Admin admin){
        //密码加密
        admin.setPassword(ShiroKit.md5(admin.getPassword(), admin.getUname()));
        adminService.add(admin);
        return "redirect:/admin/list";
    }

    //进入教师修改界面
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Model model){
        Admin admin = adminService.findById(id);
        model.addAttribute("admin",admin);
        return "shouye/teacherID_update";
    }
    //完成教师账号修改
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Admin admin){
        //密码加密
        admin.setPassword(ShiroKit.md5(admin.getPassword(), admin.getUname()));
        adminService.add(admin);
        System.out.println("修改成功！");
        return "redirect:/admin/list";
    }

    //删除数据
    @GetMapping(value = "/{id}/delete")
    public String delete(@PathVariable int id){
        adminService.delete(id);
        System.out.println("删除成功！");
        return "redirect:/admin/list";
    }
}
