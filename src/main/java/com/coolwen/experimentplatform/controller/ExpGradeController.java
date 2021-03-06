/**
 * 文件名：LearningeffectController.java
 * 修改人：xxxx
 * 修改时间：
 * 修改内容：新增
 */

package com.coolwen.experimentplatform.controller;
import com.coolwen.experimentplatform.model.DTO.ModuleGradesDto;
import com.coolwen.experimentplatform.model.Student;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import com.coolwen.experimentplatform.model.TotalScorePass;
import com.coolwen.experimentplatform.service.StudentService;
import com.coolwen.experimentplatform.service.TotalScoreCurrentService;
import com.coolwen.experimentplatform.service.TotalScorePassService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
*@Description 实验大厅中，学生查询模块成绩和总成绩
*@Author 朱治汶
*@Version 1.0
*@Date 2020/5/29 15:49
*/
@Controller
@RequestMapping(value = "/grade")
public class ExpGradeController {

    @Autowired
    TotalScoreCurrentService totalScoreCurrentService;  //考核成绩查询的service层

    @Autowired
    StudentService studentService; //注入学生班级查询

    @Autowired
    TotalScorePassService totalScorePassService; //往期考核成绩查询service层

    /**
     * 学生查询模块成绩和总成绩
     * @param model 存储成绩数据，将数据展示到对应页面
     * @return 跳转到实验大厅--》查看实验成绩 页面
     */
    @GetMapping(value = "/score")
    public String totalscore(Model model, HttpSession session){
        //获取学生的登录信息
//        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        Student student = (Student) session.getAttribute("student");
        int stuId = student.getId();

        if(student.getClassId()>0) {
            //检查此学生有没有考核资格
            List<Student> studentOne = studentService.findStudentIsCurrentkaoheByStuid(stuId);
            System.out.println(">>>>>>>>>>>>"+studentOne.size());
            if(studentOne.size()>0) {
                //查询该学生的考核实验模块成绩
                List<ModuleGradesDto> moduleGrades = totalScoreCurrentService.ModuleGrade(student.getId());
                model.addAttribute("ModuleGrades", moduleGrades);
                //查询该学生的考核模块和理论成绩的总评成绩
                List<TotalScoreCurrent> totalScoreCurrents = totalScoreCurrentService.findeAllBystuid(student.getId());
                model.addAttribute("totalScoreCurrents", totalScoreCurrents);
            }
            else
            {
                //查询该学生的考核模块和理论成绩的总评成绩
                List<TotalScorePass> totalScorePass = totalScorePassService.findByStuId(stuId);
                model.addAttribute("totalScoreCurrents",totalScorePass);

                //查询该学生的考核实验模块成绩
                ModuleGradesDto[] moduleGrades = new ModuleGradesDto[totalScorePass.get(0).getKaoheNum()];
                //考核模块名
                String[] kaohename =  totalScorePass.get(0).getKaoheName().split(";");
                //考核模块测试分数
                String[] kaohetest =  totalScorePass.get(0).getKaoheMtestscore().split(";");
                //考核模块测试百分比
                String[] kaohetestbaifenbi =  totalScorePass.get(0).getKaoheMtestscoreBaifengbi().split(";");
                //考核模块报告分数
                String[] kaohereport =  totalScorePass.get(0).getKaoheMreportscore().split(";");
                //考核模块报告百分比
                String[] kaohereportbaifenbi =  totalScorePass.get(0).getKaoheMreportscoreBaifengbi().split(";");
                for (int i = 0; i < totalScorePass.get(0).getKaoheNum(); i++) {
                    moduleGrades[i] = new ModuleGradesDto(
                            i+1,
                            kaohename[i],
                            Float.parseFloat(kaohetest[i]),
                            Float.parseFloat(kaohetestbaifenbi[i]),
                            Float.parseFloat(kaohereport[i]),
                            Float.parseFloat(kaohereportbaifenbi[i]),
                            Float.parseFloat(String.format("%.1f",Float.parseFloat(kaohetest[i])*Float.parseFloat(kaohetestbaifenbi[i]) +
                                    Float.parseFloat(kaohereport[i])*Float.parseFloat(kaohereportbaifenbi[i]))));
//                    System.out.println(Float.parseFloat(kaohetest[i])+">>>"+Float.parseFloat(kaohetestbaifenbi[i]) +
//                            ">>>"+Float.parseFloat(kaohereport[i])+">>>"+Float.parseFloat(kaohereportbaifenbi[i]));
//                    System.out.println(moduleGrades[i].getM_score());
                }
                model.addAttribute("ModuleGrades", moduleGrades);
            }
        }
        return "home_shiyan/grade";
    }

    /**
     *跳转实验大厅考核模块的接口，带学生id，
     *@return 考核模块接口（ExpModelController.java 中 kaoModelById方法）
     */
    @GetMapping(value = "/kaohe")
    public String kaohe(HttpSession session){
//        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        Student student = (Student) session.getAttribute("student");
        return "redirect:/expmodel/kaoheModel/"+student.getId();
    }
}
