package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.StudentRepository;
import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.service.StudentService;
import com.coolwen.experimentplatform.service.TotalScoreCurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

/**
 * @author Artell
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping(value = "/totalscore")
public class TotalscoreCurrentController {

    @Autowired
    public TotalScoreCurrentService totalScoreCurrentService;

    @Autowired
    public StudentService studentService;
//    @GetMapping("/test")
//    public String hello() {
//        return "AllModel";
//    }
//
//    /**
//     *
//     *
//     */
//    @GetMapping("/list")
//    public String expModelList(Model model,@RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
//        model.addAttribute("page",totalScoreCurrentService.listTotalScoreCurrent());
//        return "totalscore/list";
//    }

//    @GetMapping(value = "/score_manage")
//    public String loadAllModel(Model model) {
////        Pageable pageable = PageRequest.of(pageNum, 5);
////        ExpModel expModel = expModelService.findModelList(pageNum);
////        Page<KaoheModel> page = kaoheModelService.findAll(pageable);
////        model.addAttribute("allKaohe", page);
////        List<Integer> check = kaoheModelService.inKaoheList();
////        model.addAttribute("allKaohe",expModelService.findModelList(pageNum));
////        model.addAttribute("checkList",check);
//
//        List<StudentTestScoreDTO> a = studentRepository.listStudentMTestAnswerDTO();
//        model.addAttribute("allInfo",a);
//        return "kaohe/score_manage";
//    }

    @GetMapping("/list")
    public String expModelList(Model model, @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        Page<StuTotalScoreCurrentDTO> totalScore= studentService.listStuTotalScoreCurrentDTO(pageNum);
        model.addAttribute("pageTotalScore",totalScore);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+totalScore);
        return "kaohe/all_score";
    }



}
