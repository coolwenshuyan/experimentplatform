package com.coolwen.experimentplatform.model.DTO;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */

public class StuTotalScoreCurrentDTO {

    @Excel(name = "学号", orderNum = "0")
    private String xuehao;

    @Excel(name = "姓名", orderNum = "1")
    private String sName;

    @Excel(name = "班级", orderNum = "2")
    private String className;

    @Excel(name = "模块总分", orderNum = "3")
    private float mTotalScore;

    @Excel(name = "综合测试得分", orderNum = "4")
    private float testScore;

    @Excel(name = "期末总分", orderNum = "5")
    private float totalScore;

    public StuTotalScoreCurrentDTO() {
    }

    public StuTotalScoreCurrentDTO(String xuehao, String sName, String className, float mTotalScore, float testScore, float totalScore) {
        this.xuehao = xuehao;
        this.sName = sName;
        this.className = className;
        this.mTotalScore = mTotalScore;
        this.testScore = testScore;
        this.totalScore = totalScore;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getmTotalScore() {
        return mTotalScore;
    }

    public void setmTotalScore(float mTotalScore) {
        this.mTotalScore = mTotalScore;
    }

    public float getTestScore() {
        return testScore;
    }

    public void setTestScore(float testScore) {
        this.testScore = testScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "StuTotalScoreCurrentDTO{" +
                "xuehao='" + xuehao + '\'' +
                ", sName='" + sName + '\'' +
                ", className='" + className + '\'' +
                ", mTotalScore=" + mTotalScore +
                ", testScore=" + testScore +
                ", totalScore=" + totalScore +
                '}';
    }
}
