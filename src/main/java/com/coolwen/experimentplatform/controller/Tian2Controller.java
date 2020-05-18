package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.ClassModel;
import com.coolwen.experimentplatform.model.DTO.StudentLastTestScoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Artell
 * @version 2020/5/17 18:04
 */

@Controller
@RequestMapping(value = "/tian")
public class Tian2Controller {


    @Autowired
    private HttpServletRequest request;

    @GetMapping(value = "/list")
    public String loadAllScore(Model model) {
        model.addAttribute("t1", "2222");

        return "home_shiyan/tian2";
    }

    @PostMapping(value = "/list")
    public String loadAllScore2(HttpServletRequest request,
                                Model model,
                                @RequestParam(required = true, defaultValue = "kong") String test1) {


        System.out.println("test1:>>>>>>>" + test1);
        System.out.println("test1:>>>>>>>" + request);
//        Enumeration em = request.getParameterNames();
//        while (em.hasMoreElements()) {
//        String name = (String) em.nextElement();
//        String value = request.getParameter(test1);
//            System.out.println(value);
//        }

        String value = request.getParameter("test1");
        System.out.println("ttttttttt:>>>>>>>"+value);


        return "home_shiyan/tian2";
    }
}
