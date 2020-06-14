package com.coolwen.experimentplatform.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 朱治汶
 * @version 1.0
 * @date 2020/6/13 22:06
 **/
@Entity
@Table(name = "t_college_report")
public class CollegeReport {
    @Id //自动获取id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_id")
    @TableGenerator(name = "admin_id", initialValue = 0, allocationSize = 1, table = "seq_table")
    @Column(name = "id")
    private int id;
    @Column(name = "stu_id")
    //学生ID
    private int stuid;
    @Column(name = "m_id")
    //模块ID
    private int mid;
    @Column(name = "cr_classname")
    //课程名称
    private String crClassName;
    @Column(name = "cr_dress")
    //实验地点
    private String crDress;
    @Column(name = "cr_date")
    //报告地点
    private Date crDate;
    @Column(name = "cr_teacher")
    //指导教师
    private String crTeacher;
    @Column(name = "cr_exp_purpose",columnDefinition = "text")
    //实验目的
    private String crExpPurpose;
    @Column(name = "cr_exp_evr",columnDefinition = "text")
    //实验环境
    private String crExpEvr;
    @Column(name = "cr_exp_content",columnDefinition = "longtext")
    //实验内容和步骤
    private String crExpContent;
    @Column(name = "cr_exp_summary",columnDefinition = "text")
    //实验心得和总结
    private String crExpSummary;
    @Column(name = "cr_tc_comment",columnDefinition = "text")
    //教师评语
    private String crTcComment;
    @Column(name = "cr_score",columnDefinition = "int default 0")
    //报告成绩
    private Float crScore;
    @Column(name = "cr_tc_state",columnDefinition = "int default 0")
    //教师评分状态，0未评分，1已经评分
    private Boolean crTcState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
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
}
