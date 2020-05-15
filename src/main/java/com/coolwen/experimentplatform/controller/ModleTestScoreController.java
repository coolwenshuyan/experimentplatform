package com.coolwen.experimentplatform.controller;

        import com.coolwen.experimentplatform.dao.StudentRepository;
        import com.coolwen.experimentplatform.model.StudentTestScoreDTO;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

/**
 * @author Artell
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping(value = "/testScoreManage")
public class ModleTestScoreController {

    @Autowired
    public StudentRepository studentRepository;


    @GetMapping(value = "/list")
    public String loadAllModel(Model model) {
        List<StudentTestScoreDTO> a = studentRepository.listStudentMTestAnswerDTO();
        model.addAttribute("allInfo",a);
        return "kaohe/score_manage";
    }



}
