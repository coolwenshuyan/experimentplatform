package com.coolwen.experimentplatform.model.DTO;

import com.google.inject.internal.cglib.core.$ProcessArrayCallback;

/**
 * @author Artell
 * @version 2020/5/13 22:34
 */


public class StudentTestScoreDTO {
//    学生id
    private int sid;
//    真实姓名
    private String sName;
//    班级号
    private int sClass;
//    实验名称
    private String mName;
//    成绩
    private float mScore;
//   完成状态
    private boolean Done;

    public StudentTestScoreDTO(int sid, String sName, int sClass, String mName, float mScore, boolean done) {
        this.sid = sid;
        this.sName = sName;
        this.sClass = sClass;
        this.mName = mName;
        this.mScore = mScore;
        Done = done;
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

    @Override
    public String toString() {
        return "StudentTestScoreDTO{" +
                "sid=" + sid +
                ", sName='" + sName + '\'' +
                ", sClass=" + sClass +
                ", mName='" + mName + '\'' +
                ", mScore=" + mScore +
                ", Done=" + Done +
                '}';
    }
}
