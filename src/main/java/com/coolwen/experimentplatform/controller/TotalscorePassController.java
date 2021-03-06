package com.coolwen.experimentplatform.controller;

import com.coolwen.experimentplatform.dao.TotalScorePassRepository;
import com.coolwen.experimentplatform.filter.FileExcelUtil;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.OneModelScoreDTO;
import com.coolwen.experimentplatform.model.DTO.PassTotalScoreDTO;
import com.coolwen.experimentplatform.model.DTO.StuTotalScoreCurrentDTO;
import com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO;
import com.coolwen.experimentplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 总成绩管理
 * 列出往期所有的成绩
 * @author 王雨来
 * @version 2020/5/13 12:21
 */

@Controller
@RequestMapping(value = "/passTotalscore")
public class TotalscorePassController {

    @Autowired
    public TotalScoreCurrentService totalScoreCurrentService;

    @Autowired
    public TotalScorePassService totalScorePassService;

    @Autowired
    public StudentService studentService;

    @Autowired
    public KaoheModelService kaoheModelService;

    @Autowired
    public ClazzService clazzService;

    /**
     * 列出所有成绩
     * @param model 传值
     * @param pageNum 分页
     * @return 页面
     */
/*    @GetMapping("/list")
    public String expModelList(Model model, @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        Page<TotalScorePass> totalScorePasses = totalScorePassService.findAll(pageNum);
        for(TotalScorePass i:totalScorePasses){
            System.out.println(i);

        Student student = studentService.findStudentById(i.getStuId());
        ClassModel classModel = clazzService.findById(student.getId());

        List<OneModelScoreDTO> oneModelScoreDTOS;
//        PassTotalScoreDTO passTotalScoreDTO = new(student.getStuXuehao(),student.getStuName(),classModel.getClassName(),oneModelScoreDTOS,i.getmTotalScore(),oneModelScoreDTOS,i.get;);


        }
        return null;
    }*/

    @GetMapping("/list")
    public String expModelList(Model model,
                               @RequestParam(required = true, defaultValue = "")String select_orderId ,
                               @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        //从数据库得到所有的总成绩

        Page<StuTotalScoreCurrentDTO> totalScore= studentService.listStuTotalScoreCurrentDTOOfPass(pageNum,select_orderId);
        model.addAttribute("selectOrderId",select_orderId);

        //获得所有往期班级
        List<ClassModel> classList = clazzService.findPassClass();
        model.addAttribute("classList",classList);

        model.addAttribute("pageTotalScore",totalScore);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+totalScore);
        return "kaohe/all_score_pass";
    }

    @GetMapping("/{classId}/list")
    public String getTotalScoreCirrentByGroupId(Model model,
                                                @PathVariable int classId,
                                                @RequestParam(required = true, defaultValue = "")String select_orderId ,
                                                @RequestParam(value = "pageNum",defaultValue = "0",required = true) int pageNum){
        //从数据库得到所有的总成绩
        Page<StuTotalScoreCurrentDTO> totalScore= studentService.listStuTotalScoreCurrentDTOOfPassByClassId(pageNum,select_orderId,classId);


        //获得所有往期班级
        List<ClassModel> classList = clazzService.findPassClass();
        model.addAttribute("classList",classList);
        model.addAttribute("classId",classId);

        model.addAttribute("pageTotalScore",totalScore);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+totalScore);
        return "kaohe/all_score_pass";
    }


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
        List<StuTotalScoreCurrentDTO> totalScore= studentService.listAllStuTotalScoreCurrentDTOOfPass();

//        List<Student> b = studentRepository.findAll();
        // 设置响应输出的头类型(设置响应类型)
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称(设置下载文件的默认名称)
        response.setHeader("Content-Disposition", "attachment;filename=address.xls");
        //导出操作
//        try {
//            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("客户地址","1"),User.class,addresses);
//            workbook.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FileExcelUtil.exportExcel(totalScore, "往期成绩汇总", "往期成绩", StuTotalScoreCurrentDTO.class, "往期成绩表.xls", response);
    }
}
