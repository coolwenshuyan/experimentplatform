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
import com.coolwen.experimentplatform.service.StudentService;
import com.coolwen.experimentplatform.service.TotalScoreCurrentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 学生查询模块成绩和总成绩
     * @param model 存储成绩数据，将数据展示到对应页面
     * @return 跳转到实验大厅--》查看实验成绩 页面
     */
    @GetMapping(value = "/score")
    public String totalscore(Model model){
        //获取学生的登录信息
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        int stuId = student.getId();

        if(student.getClassId()>0) {
            //检查此学生有没有考核资格
            List<Student> studentOne = studentService.findStudentIsCurrentkaoheByStuid(stuId);

            if(studentOne.size()>0) {
                //查询该学生的考核实验模块成绩
                List<TotalScoreCurrent> totalScoreCurrents = totalScoreCurrentService.findeAllBystuid(student.getId());
                model.addAttribute("totalScoreCurrents", totalScoreCurrents);
                //查询该学生的考核模块和理论成绩的总评成绩
                List<ModuleGradesDto> ModuleGrades = totalScoreCurrentService.ModuleGrade(student.getId());
                model.addAttribute("ModuleGrades", ModuleGrades);
            }
            else
            {
                //这里要改，还未完成,没完成
                List<TotalScoreCurrent> totalScoreCurrents = totalScoreCurrentService.findeAllBystuid(student.getId());
                model.addAttribute("totalScoreCurrents", totalScoreCurrents);
                //查询该学生的考核模块和理论成绩的总评成绩
                List<ModuleGradesDto> ModuleGrades = totalScoreCurrentService.ModuleGrade(student.getId());
                model.addAttribute("ModuleGrades", ModuleGrades);
            }
        }
        return "home_shiyan/grade";
    }

    /**
     *跳转实验大厅考核模块的接口，带学生id，
     *@return 考核模块接口（ExpModelController.java 中 kaoModelById方法）
     */
    @GetMapping(value = "/kaohe")
    public String kaohe(){
        Student student = (Student) SecurityUtils.getSubject().getPrincipal();
        return "redirect:/expmodel/kaoheModel/"+student.getId();
    }
}
