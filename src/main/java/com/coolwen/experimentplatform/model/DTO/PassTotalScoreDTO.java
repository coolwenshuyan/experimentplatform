package com.coolwen.experimentplatform.model.DTO;

import java.util.List;

/**
 * @author Artell
 * @version 2020/6/9 16:40
 */

//暂时废弃
public class PassTotalScoreDTO {
    private String xueHao;
    private String name;
    private String className;
    private List<OneModelScoreDTO> oneModelInfo;
    private float allMScore;
    private float testScore;
    private float allMScoreBaiFenBi;
    private float testScoreBaiFenBi;
    private float totalScorePass;
    private float time;


    public PassTotalScoreDTO(String xueHao, String name, String className, List<OneModelScoreDTO> oneModelInfo, float allMScore, float testScore, float allMScoreBaiFenBi, float testScoreBaiFenBi, float totalScorePass, float time) {
        this.xueHao = xueHao;
        this.name = name;
        this.className = className;
        this.oneModelInfo = oneModelInfo;
        this.allMScore = allMScore;
        this.testScore = testScore;
        this.allMScoreBaiFenBi = allMScoreBaiFenBi;
        this.testScoreBaiFenBi = testScoreBaiFenBi;
        this.totalScorePass = totalScorePass;
        this.time = time;
    }

    public String getXueHao() {
        return xueHao;
    }

    public void setXueHao(String xueHao) {
        this.xueHao = xueHao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<OneModelScoreDTO> getOneModelInfo() {
        return oneModelInfo;
    }

    public void setOneModelInfo(List<OneModelScoreDTO> oneModelInfo) {
        this.oneModelInfo = oneModelInfo;
    }

    public float getAllMScore() {
        return allMScore;
    }

    public void setAllMScore(float allMScore) {
        this.allMScore = allMScore;
    }

    public float getTestScore() {
        return testScore;
    }

    public void setTestScore(float testScore) {
        this.testScore = testScore;
    }

    public float getAllMScoreBaiFenBi() {
        return allMScoreBaiFenBi;
    }

    public void setAllMScoreBaiFenBi(float allMScoreBaiFenBi) {
        this.allMScoreBaiFenBi = allMScoreBaiFenBi;
    }

    public float getTestScoreBaiFenBi() {
        return testScoreBaiFenBi;
    }

    public void setTestScoreBaiFenBi(float testScoreBaiFenBi) {
        this.testScoreBaiFenBi = testScoreBaiFenBi;
    }

    public float getTotalScorePass() {
        return totalScorePass;
    }

    public void setTotalScorePass(float totalScorePass) {
        this.totalScorePass = totalScorePass;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }



    @Override
    public String toString() {
        return "PassTotalScoreDTO{" +
                "xueHao='" + xueHao + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", oneModelInfo=" + oneModelInfo +
                ", allMScore=" + allMScore +
                ", testScore=" + testScore +
                ", allMScoreBaiFenBi=" + allMScoreBaiFenBi +
                ", testScoreBaiFenBi=" + testScoreBaiFenBi +
                ", totalScorePass=" + totalScorePass +
                ", time=" + time +
                '}';
    }
}
