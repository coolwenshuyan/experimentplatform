package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.Resource;
import com.coolwen.experimentplatform.model.Role;
import com.coolwen.experimentplatform.model.User;
import com.coolwen.experimentplatform.service.RoleService;
import com.coolwen.experimentplatform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoolWen
 * @version 2018-11-01 7:16
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
    protected static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.listRole());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(User user, HttpServletRequest req, Model model) {
        logger.debug("添加用户信息:" + user.toString());
        String[] trids = req.getParameterValues("rids");
        List<Integer> rids = new ArrayList<Integer>();
        for (String rid : trids) {
            rids.add(Integer.parseInt(rid));
        }
        logger.debug("添加用户信息的角色" + rids);
        userService.add(user, rids);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("updateStatus/{id}")
    public String updateStatus(@PathVariable int id) {
        User u = userService.load(id);
        if (u.getStatus() == false) {
            u.setStatus(true);
        } else {
            u.setStatus(false);
        }
        userService.update(u);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        User user = userService.load(id);
        model.addAttribute("user", user);
//        logger.debug("所有角色roles:" + roleService.listRole().toString());
        model.addAttribute("roles", roleService.listRole());
        List<Role> hasRoles = userService.listUserRole(id);
        List<Integer> rids = new ArrayList<Integer>();
        for (Role r : hasRoles) {
            rids.add(r.getId());
        }
//        logger.debug("用户拥有的角色rids:" + rids);
        model.addAttribute("hasRole", rids);
        return "user/update";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, HttpServletRequest req, User user) {
        logger.debug("更新用户的:" + user);
        User tu = userService.load(id);
        tu.setNickname(user.getNickname());
        tu.setStatus(user.getStatus());
//        tu.setPassword(user.getPassword());
        String[] trids = req.getParameterValues("rids");
        List<Integer> rids = new ArrayList<Integer>();
        for (String rid : trids) {
            rids.add(Integer.parseInt(rid));
        }
        logger.debug("更新用户的:rids" + rids);
        userService.update(tu, rids);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/listRes/{id}")
    public String listRes(Model model, @PathVariable int id) {
        List<Resource> hasRes = userService.listAllResource(id);
        logger.debug("hasRes:" + hasRes);
        logger.debug("userID:" + id);
        model.addAttribute("reses", hasRes);
        model.addAttribute("user", userService.load(id));
        return "user/res";
    }
}
