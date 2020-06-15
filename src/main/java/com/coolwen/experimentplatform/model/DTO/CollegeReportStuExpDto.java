package com.coolwen.experimentplatform.model.DTO;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 朱治汶
 * @date 2020/6/14 0:41
 **/
public class CollegeReportStuExpDto {
    private int mid;
    //课程名称
    private String crClassName;
    //实验地点
    private String crDress;
    //报告地点
    private Date crDate;
    //指导教师
    private String crTeacher;
    //实验目的
    private String crExpPurpose;
    //实验环境
    private String crExpEvr;
    //实验内容和步骤
    private String crExpContent;
    //实验心得和总结
    private String crExpSummary;
    //教师评语
    private String crTcComment;
    //报告成绩
    private Float crScore;
    //教师评分状态，0未评分，1已经评分
    private Boolean crTcState;
    //学生姓名
    private String stuName;
    //学号
    private String stuXuehao;
    //班级名字
    private String className;
    //模块名称
    private String mname;

    public CollegeReportStuExpDto(int mid, String crClassName, String crDress, Date crDate, String crTeacher,
                                  String crExpPurpose, String crExpEvr, String crExpContent, String crExpSummary,
                                  String crTcComment, Float crScore, Boolean crTcState, String stuName, String stuXuehao,
                                  String className, String mname) {
        this.mid = mid;
        this.crClassName = crClassName;
        this.crDress = crDress;
        this.crDate = crDate;
        this.crTeacher = crTeacher;
        this.crExpPurpose = crExpPurpose;
        this.crExpEvr = crExpEvr;
        this.crExpContent = crExpContent;
        this.crExpSummary = crExpSummary;
        this.crTcComment = crTcComment;
        this.crScore = crScore;
        this.crTcState = crTcState;
        this.stuName = stuName;
        this.stuXuehao = stuXuehao;
        this.className = className;
        this.mname = mname;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getCrClassName() {
        return crClassName;
    }

    public void setCrClassName(String crClassName) {
        this.crClassName = crClassName;
    }

    public String getCrDress() {
        return crDress;
    }

    public void setCrDress(String crDress) {
        this.crDress = crDress;
    }

    public Date getCrDate() {
        return crDate;
    }

    public void setCrDate(Date crDate) {
        this.crDate = crDate;
    }

    public String getCrTeacher() {
        return crTeacher;
    }

    public void setCrTeacher(String crTeacher) {
        this.crTeacher = crTeacher;
    }

    public String getCrExpPurpose() {
        return crExpPurpose;
    }

    public void setCrExpPurpose(String crExpPurpose) {
        this.crExpPurpose = crExpPurpose;
    }

    public String getCrExpEvr() {
        return crExpEvr;
    }

    public void setCrExpEvr(String crExpEvr) {
        this.crExpEvr = crExpEvr;
    }

    public String getCrExpContent() {
        return crExpContent;
    }

    public void setCrExpContent(String crExpContent) {
        this.crExpContent = crExpContent;
    }

    public String getCrExpSummary() {
        return crExpSummary;
    }

    public void setCrExpSummary(String crExpSummary) {
        this.crExpSummary = crExpSummary;
    }

    public String getCrTcComment() {
        return crTcComment;
    }

    public void setCrTcComment(String crTcComment) {
        this.crTcComment = crTcComment;
    }

    public Float getCrScore() {
        return crScore;
    }

    public void setCrScore(Float crScore) {
        this.crScore = crScore;
    }

    public Boolean getCrTcState() {
        return crTcState;
    }

    public void setCrTcState(Boolean crTcState) {
        this.crTcState = crTcState;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuXuehao() {
        return stuXuehao;
    }

    public void setStuXuehao(String stuXuehao) {
        this.stuXuehao = stuXuehao;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
