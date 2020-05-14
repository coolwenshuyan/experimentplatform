package com.coolwen.experimentplatform.controller;


import com.coolwen.experimentplatform.dao.AdminDao;
import com.coolwen.experimentplatform.model.Admin;
import com.coolwen.experimentplatform.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminDao adminDao;
    @Autowired
    AdminService adminService;

    //查询所有数据
    @GetMapping(value = "/list")
    public String AdminList(Model model, @RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 5);
        Page<Admin> page = adminDao.findAll(pageable);
        model.addAttribute("AdminPageInfo", page);

        return "admin/list";
    }
    //点击添加账号，跳转到添加界面
    @GetMapping(value = "/add")
    public String AdminAdd(){
        return "admin/add";
    }

    //完成添加操作
    @PostMapping(value = "/add")
    public String add(Admin admin){
        adminService.add(admin);
        return "redirect:/admin/list";
    }

    //进入修改界面
    @GetMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Model model){
        Admin admin = adminService.findById(id);
        model.addAttribute("admin",admin);
        return "/admin/update";
    }
    //完成修改
    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable int id,Admin admin){
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
