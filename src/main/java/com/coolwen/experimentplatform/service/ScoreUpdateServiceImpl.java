package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreUpdateServiceImpl implements ScoreUpdateService {
    @Autowired
    ModuleTestAnswerStuService moduleTestAnswerStuService;
    @Autowired
    KaoheModelService kaoheModelService;
    @Autowired
    KaoHeModelScoreService kaoHeModelScoreService;
    @Autowired
    ModuleTestQuestService moduleTestQuestService;
    @Autowired
    ReportService reportService;
    @Autowired
    ReportAnswerService reportAnswerService;
    @Autowired
    TotalScoreCurrentService totalScoreCurrentService;
    @Autowired
    StudentService studentService;
    @Autowired
    ClazzService clazzService;

    @Autowired
    ExpModelService expModelService; //模块服务

    @Autowired
    CollegeReportService collegeReportService; //学院报告服务

    @Override
    public void singleStudentScoreUpdate(int id) {
        //判断学生班级是否往期
        ClassModel classModel = clazzService.findClassModelByStuId(id);
        if(classModel.getClassIscurrent() == true){
            return;
        }
        //找到所有考试模块
        List<KaoheModel> kaoheModelList = kaoheModelService.findAll();
        //存储分数
        float mTestScore = 0;
        float mReportScore = 0;
        float mTotalScore = 0;
        float testScore = 0;
        float test_baifenbi = 0;
        float kaohe_baifenbi = 0;
        for(KaoheModel k : kaoheModelList){
            //循环存储，减少数据库操作
            test_baifenbi = k.getTest_baifenbi();
            kaohe_baifenbi = k.getKaohe_baifenbi();

            //循环前，每个模块测试成绩和报告成绩设置为0
            mTestScore = 0;
            mReportScore = 0;

            //找到该考生在该模块的考核模块成绩表
            KaoHeModelScore kaoHeModelScore = kaoHeModelScoreService.findKaoheModelScoreByMid(k.getM_id(),id);

            //模块测试没有做，不进行统计
            if (kaoHeModelScore.ismTeststate()){
                //模块题目比对获取分数
                mTestScore = moduleTestScore(k.getM_id(),id);
            }
            //模块报告没有做，不进行统计
            if (kaoHeModelScore.ismReportstate()){
                //模块报告分数获取
                mReportScore = moduleReportScore(k.getM_id(),id);
            }

            //更新模块测试分数
            kaoHeModelScore.setmTestScore(mTestScore);
            //更新模块报告分数
            kaoHeModelScore.setmReportScore(mReportScore);
            //更新模块成绩
            kaoHeModelScore.setmScore(mTestScore * k.getM_test_baifenbi() + mReportScore * k.getM_report_baifenbi());
            //存储考核模块成绩
            kaoHeModelScoreService.add(kaoHeModelScore);
            //更新整体模块成绩
            mTotalScore += kaoHeModelScore.getmScore() * k.getM_scale();

        }
        //找到该学生的当期总评成绩表
        TotalScoreCurrent totalScoreCurrent = totalScoreCurrentService.findTotalScoreCurrentByStuId(id);
        //更新整体模块成绩
        totalScoreCurrent.setmTotalScore(mTotalScore);

        //计算课程整体测试成绩
        testScore = moduleTestScore(-1,id);
        //更新整体测试成绩
        totalScoreCurrent.setTestScore(testScore);
        //更新总成绩
        totalScoreCurrent.setTotalScore(mTotalScore * kaohe_baifenbi + testScore * test_baifenbi);
        totalScoreCurrentService.add(totalScoreCurrent);
    }

    @Override
    public void allStudentScoreUpdate() {
        List<Student> studentList = studentService.findStudentByNotClassId();
        for(Student s : studentList){
            System.out.println(s);
            singleStudentScoreUpdate(s.getId());
        }
    }


    //返回模块题目比对后的分数
    public float moduleTestScore(int mid,int stuid){
        float mTestScore = 0;
        List<ModuleTestQuest> moduleTestQuestList = moduleTestQuestService.find(mid);
        for (ModuleTestQuest m : moduleTestQuestList){
            ModuleTestAnswerStu moduleTestAnswerStu = moduleTestAnswerStuService.findModuleTestAnswerStuByStu_idAndQuest_id(stuid,m.getQuestId());
            //如果没有答题记录，则不进行统计
            if (moduleTestAnswerStu != null){
                if(moduleTestAnswerStu.getStu_quest_answer().equals(m.getQuestAnswer())){
                    mTestScore += m.getQuestScore();
                    moduleTestAnswerStu.setScore((int) m.getQuestScore());
                }else {
                    moduleTestAnswerStu.setScore(0);
                }
                moduleTestAnswerStuService.add(moduleTestAnswerStu);
            }
        }
        return mTestScore;
    }

    //模块报告分数获取
    public float moduleReportScore(int mid,int stuid){
        float mReportScore = 0;
        //根据模块的报告类型，如果是学院报告，就从学院报告表中查询，如果是自定义报告，就从自定义报告中查询
        ExpModel model1 = expModelService.findExpModelByID(mid);
//        System.out.println(model1.isReport_type());
//        System.out.println("mid:"+mid);
        if(model1.isReport_type()) {
            CollegeReport collegeReport1 = collegeReportService.findStuidAndMid(stuid,mid);
            mReportScore = collegeReport1.getCrScore();
        } else {
            List<Report> reportList = reportService.findReportByMId(mid);
            for (Report r : reportList) {
                ReportAnswer reportAnswer = reportAnswerService.findByReportidAndStuID(r.getReportId(), stuid);
                mReportScore += reportAnswer.getScore();
            }
        }
        return mReportScore;
    }




}
