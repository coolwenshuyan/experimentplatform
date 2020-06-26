package com.coolwen.experimentplatform.model.DTO;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author Artell
 * @version 2020/5/13 22:34
 */


public class StudentReportScoreDTO {
//    学生id
    @Excel(name = "学生id", orderNum = "0")
    private int sid;
//    真实姓名
    @Excel(name = "学生姓名", orderNum = "1")
    private String sName;
//    班级号
    @Excel(name = "班级号", orderNum = "2")
    private int sClass;
//    实验名称
    @Excel(name = "实验名称", orderNum = "3")
    private String mName;
//    成绩
    @Excel(name = "测试成绩", orderNum = "4")
    private float mScore;
//   完成状态
    private boolean Done;

    private int mid;

    private boolean reportType;

    public StudentReportScoreDTO(int sid, String sName, int sClass, String mName, float mScore, boolean done, int mid) {
        this.sid = sid;
        this.sName = sName;
        this.sClass = sClass;
        this.mName = mName;
        this.mScore = mScore;
        Done = done;
        this.mid = mid;
    }

    public StudentReportScoreDTO(int sid, String sName, int sClass, String mName, float mScore, boolean done, int mid, boolean reportType) {
        this.sid = sid;
        this.sName = sName;
        this.sClass = sClass;
        this.mName = mName;
        this.mScore = mScore;
        Done = done;
        this.mid = mid;
        this.reportType = reportType;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsClass() {
        return sClass;
    }

    public void setsClass(int sClass) {
        this.sClass = sClass;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public float getmScore() {
        return mScore;
    }

    public void setmScore(float mScore) {
        this.mScore = mScore;
    }

    public boolean isDone() {
        return Done;
    }

    public void setDone(boolean done) {
        Done = done;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public boolean isReportType() {
        return reportType;
    }

    public void setReportType(boolean reportType) {
        this.reportType = reportType;
    }

    @Override
    public String toString() {
        return "StudentTestScoreDTO{" +
                "sid=" + sid +
                ", sName='" + sName + '\'' +
                ", sClass=" + sClass +
                ", mName='" + mName + '\'' +
                ", mScore=" + mScore +
                ", Done=" + Done +
                ", mid=" + mid +
                '}';
    }
}
