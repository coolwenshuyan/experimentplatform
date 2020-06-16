package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.model.CollegeReport;
import com.coolwen.experimentplatform.model.DTO.CollegeReportStuExpDto;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.service.CollegeReportService;
import com.coolwen.experimentplatform.service.KaoHeModelScoreService;
import com.coolwen.experimentplatform.service.ScoreUpdateService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 朱治汶
 * @date 2020/6/13 23:35
 **/
@Controller
@RequestMapping(value = "/collegereport")
public class CollegeReportController {

    @Autowired
    CollegeReportService collegeReportService;

    @Autowired
    KaoHeModelScoreService kaoHeModelScoreService;

    @Autowired
    ScoreUpdateService scoreUpdateService;

    /**
     * 进入填写实验目的页面
     * @param mid  模块id号
     * @param model  返回报告信息
     * @return 进入实验报告目的页面
     */
    @GetMapping("/info/{mid}")
    public String info(@PathVariable int mid, Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        CollegeReport collegeReport = collegeReportService.findStuidAndMid(student.getId(),mid);
        //如果没有记录，则创建一条数据
        if (collegeReport == null){
            CollegeReport collegeReport1 = new CollegeReport();
            collegeReport1.setStuid(student.getId());
            collegeReport1.setMid(mid);
            collegeReportService.addCollegeReport(collegeReport1);
        }
        //如果是考核模块，并教师已经评分，直接跳转到查看页面
        try {
            KaoHeModelScore khs = kaoHeModelScoreService.findKaoheModelScoreByMid(mid ,student.getId());
            if (khs.ismReportteacherstate()){
                return "redirect:/collegereport/allreport/"+mid;
            }
        }catch(Exception e){
        }
        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(), mid);
        //如果教师已经评分，直接进入查看页面
//        if (collegeReportStuExpDto.getCrTcState()){
//            return "redirect:/collegereport/allreport/"+mid;
//        }
        model.addAttribute("collegeReport",collegeReportStuExpDto);
        return "shiyan_baogao/bg_top";
    }

    /**
     * 存储填写报告的信息
     * @param mid 模块id号
     * @param collegeReport  存储前端返回的填写信息
     * @param model  存储实验报告信息
     * @return 返回到实验报告环境的填写页面
     */
    @PostMapping("/info/{mid}")
    public String info(@PathVariable int mid,CollegeReport collegeReport,Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        //添加学生填写的报告
        System.out.println(collegeReport);
        CollegeReport collegeReport1 = collegeReportService.findStuidAndMid(student.getId(),mid);
        collegeReport1.setCrDress(collegeReport.getCrDress());
        collegeReport1.setCrDate(collegeReport.getCrDate());
        collegeReport1.setCrTeacher(collegeReport.getCrTeacher());
        collegeReportService.addCollegeReport(collegeReport1);
        //查询到报告信息
        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
        model.addAttribute("collegeReport",collegeReportStuExpDto);
        return "shiyan_baogao/bg_mudi";
    }

//    @GetMapping("/purpose/{mid}")
//    public String purpose(@PathVariable int mid,CollegeReport collegeReport,Model model){
//        //获取学生的登录信息
//        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
//        //查询到报告信息
//        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
//        model.addAttribute("collegeReport",collegeReportStuExpDto);
//        return "shiyan_baogao/bg_mudi";
//    }

    @PostMapping("/purpose/{mid}")
    public String purpose1(@PathVariable int mid,CollegeReport collegeReport,Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        //添加学生填写的报告
        CollegeReport collegeReport1 = collegeReportService.findStuidAndMid(student.getId(),mid);
        collegeReport1.setCrExpPurpose(collegeReport.getCrExpPurpose());
        collegeReportService.addCollegeReport(collegeReport1);
        //查询到报告信息
        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
        model.addAttribute("collegeReport",collegeReportStuExpDto);
        return "shiyan_baogao/bg_huanjing";
    }

//    @GetMapping("/env/{mid}")
//    public String addenv(@PathVariable int mid,CollegeReport collegeReport,Model model){
//        //获取学生的登录信息
//        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
//        //查询到报告信息
//        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
//        model.addAttribute("collegeReport",collegeReportStuExpDto);
//        return "";
//    }

    @PostMapping("/env/{mid}")
    public String addenv1(@PathVariable int mid,CollegeReport collegeReport,Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        //添加学生填写的报告
        CollegeReport collegeReport1 = collegeReportService.findStuidAndMid(student.getId(),mid);
        collegeReport1.setCrExpEvr(collegeReport.getCrExpEvr());
        collegeReportService.addCollegeReport(collegeReport1);
        //查询到报告信息
        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
        model.addAttribute("collegeReport",collegeReportStuExpDto);
        return "shiyan_baogao/bg_neirong";
    }

//    @GetMapping("/content/{mid}")
//    public String content(@PathVariable int mid,CollegeReport collegeReport,Model model){
//        //获取学生的登录信息
//        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
//        //查询到报告信息
//        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
//        model.addAttribute("collegeReport",collegeReportStuExpDto);
//        return "";
//    }

    @PostMapping("/content/{mid}")
    public String content1(@PathVariable int mid,CollegeReport collegeReport,Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        //添加学生填写的报告
        CollegeReport collegeReport1 = collegeReportService.findStuidAndMid(student.getId(),mid);
        collegeReport1.setCrExpContent(collegeReport.getCrExpContent());
        collegeReportService.addCollegeReport(collegeReport1);
        //查询到报告信息
        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
        model.addAttribute("collegeReport",collegeReportStuExpDto);
        return "shiyan_baogao/bg_xinde";
    }

//    @GetMapping("/summary/{mid}")
//    public String summary(@PathVariable int mid,CollegeReport collegeReport,Model model){
//        //获取学生的登录信息
//        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
//        //查询到报告信息
//        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
//        model.addAttribute("collegeReport",collegeReportStuExpDto);
//        return "";
//    }

    @PostMapping("/summary/{mid}")
    public String summary1(@PathVariable int mid,CollegeReport collegeReport,Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        //添加学生填写的报告
        CollegeReport collegeReport1 = collegeReportService.findStuidAndMid(student.getId(),mid);
        collegeReport1.setCrExpSummary(collegeReport.getCrExpSummary());
        collegeReportService.addCollegeReport(collegeReport1);

        //如果是考核模块，改变学生填写报告状态为true
        try {
            KaoHeModelScore khs = kaoHeModelScoreService.findKaoheModelScoreByMid(mid ,student.getId());
            khs.setmReportstate(true);
            kaoHeModelScoreService.update(khs);
        }catch(Exception e){
        }
//        //查询到报告信息
//        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
//        model.addAttribute("collegeReport",collegeReportStuExpDto);
        return "redirect:/collegereport/allreport/"+mid;
    }

    @GetMapping("/allreport/{mid}")
    public String allreport(@PathVariable int mid,Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        //查询到报告信息
        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(student.getId(),mid);
        model.addAttribute("collegeReport",collegeReportStuExpDto);
        return "shiyan_baogao/bg_student";
    }

    @GetMapping("/mark/{mid}/{stuid}")
    public String mark(@PathVariable("mid") int mid,@PathVariable("stuid") int stuid,Model model){
        //查询到报告信息
        CollegeReportStuExpDto collegeReportStuExpDto = collegeReportService.findByStuidMid(stuid,mid);
        model.addAttribute("collegeReport",collegeReportStuExpDto);
        model.addAttribute("stuid",stuid);
        return "shiyan_baogao/bg_teacher";
    }

    @PostMapping("/mark/{mid}/{stuid}")
    public String mark(@PathVariable("mid") int mid,@PathVariable("stuid") int stuid,CollegeReport collegeReport){
        CollegeReport collegeReport1 = collegeReportService.findStuidAndMid(stuid,mid);
        collegeReport1.setCrTcComment(collegeReport.getCrTcComment());
        System.out.println(collegeReport.getCrTcComment());
        collegeReport1.setCrClassName(collegeReport.getCrClassName());
        collegeReport1.setCrScore(collegeReport.getCrScore());
        collegeReport1.setCrTcState(true);
        //如果是考核模块，改变学生填写报告状态为true
        try {
            KaoHeModelScore khs = kaoHeModelScoreService.findKaoheModelScoreByMid(mid ,stuid);
            khs.setmReportteacherstate(true);
            kaoHeModelScoreService.update(khs);
        }catch(Exception e){
        }
        collegeReportService.addCollegeReport(collegeReport1);
        //更新成绩
        scoreUpdateService.singleStudentScoreUpdate(stuid);
        return "redirect:/collegereport/mark/"+mid+"/"+stuid;
    }

}
