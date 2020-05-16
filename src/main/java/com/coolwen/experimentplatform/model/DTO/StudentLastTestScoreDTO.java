package com.coolwen.experimentplatform.model.DTO;

/**
 * @author Artell
 * @version 2020/5/13 22:34
 */


public class StudentLastTestScoreDTO {
//    学生id
    private String Xuehao;
//    真实姓名
    private String sName;
//    班级名
    private String sClassName;
//    总体测试成绩
    private float mScore;

    public StudentLastTestScoreDTO(String xuehao, String sName, String sClassName, float mScore) {
        Xuehao = xuehao;
        this.sName = sName;
        this.sClassName = sClassName;
        this.mScore = mScore;
    }

    public String getXuehao() {
        return Xuehao;
    }

    public void setXuehao(String xuehao) {
        Xuehao = xuehao;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsClassName() {
        return sClassName;
    }

    public void setsClassName(String sClassName) {
        this.sClassName = sClassName;
    }

    public float getmScore() {
        return mScore;
    }

    public void setmScore(float mScore) {
        this.mScore = mScore;
    }

    @Override
    public String toString() {
        return "StudentLastTestScoreDTO{" +
                "Xuehao='" + Xuehao + '\'' +
                ", sName='" + sName + '\'' +
                ", sClassName='" + sClassName + '\'' +
                ", mScore=" + mScore +
                '}';
    }
}
