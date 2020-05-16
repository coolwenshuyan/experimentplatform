package com.coolwen.experimentplatform.model.DTO;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */

public class StuTotalScoreCurrentDTO {

    private String xuehao;

    private String sName;

    private String className;

    private float mTotalScore;

    private float testScore;

    private float totalScore;

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
}
