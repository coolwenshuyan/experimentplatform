package com.coolwen.experimentplatform.controller;

        import com.coolwen.experimentplatform.dao.KaoheModelRepository;
        import com.coolwen.experimentplatform.dao.StudentRepository;
        import com.coolwen.experimentplatform.model.ExpModel;
        import com.coolwen.experimentplatform.model.KaoheModel;
        import com.coolwen.experimentplatform.model.Student;
        import com.coolwen.experimentplatform.model.StudentTestScoreDTO;
        import com.coolwen.experimentplatform.service.StudentService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
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
    @Autowired
    public KaoheModelRepository kaoheModelRepository;
    @Autowired
    public StudentService studentService;


    @GetMapping(value = "/list")
    public String loadAllModel(Model model,@RequestParam(defaultValue = "0", required=true,value = "pageNum")  Integer pageNum) {
//        List<List<StudentTestScoreDTO>> d=null;
//        List<Student> c = studentRepository.findAll();
        Page<Student> c = studentService.findAll(pageNum);
        model.addAttribute("allStu",c);


//        for (Student b:c){
//            List<StudentTestScoreDTO> a = studentRepository.listStudentMTestAnswerDTO(b.getId());
//            d.add(a);
//        }


        List<StudentTestScoreDTO> a = studentRepository.listStudentMTestAnswerDTO();
//        d.add(a);


//        List<List<StudentTestScoreDTO>> c=null;
//        for (StudentTestScoreDTO b:a){
//            int ones =b.getSid();
//            List<StudentTestScoreDTO> d = null;
//            for ()
//
//
//        }
        System.out.println(a);
        long modleNum = kaoheModelRepository.count();
        model.addAttribute("allInfo",a);
        model.addAttribute("num",modleNum);
        List<Integer> list = new ArrayList<Integer>();
        for(int i=1;i<=modleNum;i++){
            list.add(i);
        }
        System.out.println(list);
        model.addAttribute("numList",list);
        return "kaohe/score_manage";
    }



}
