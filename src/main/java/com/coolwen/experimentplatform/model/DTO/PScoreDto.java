package com.coolwen.experimentplatform.model.DTO;

public class PScoreDto   {
    private int reportid;
    //问题
    private String reportdescribe;
    //分值
    private float reportscore;
    //学生答案
    private  String stureportanswer;
    //成绩
    private int score;

    public PScoreDto(int reportid, String reportdescribe, float reportscore, String stureportanswer, int score) {
        this.reportid = reportid;
        this.reportdescribe = reportdescribe;
        this.reportscore = reportscore;
        this.stureportanswer = stureportanswer;
        this.score = score;
    }

    public int getReportid() {
        return reportid;
    }

    public void setReportid(int reportid) {
        this.reportid = reportid;
    }

    public String getReportdescribe() {
        return reportdescribe;
    }

    public void setReportdescribe(String reportdescribe) {
        this.reportdescribe = reportdescribe;
    }

    public float getReportscore() {
        return reportscore;
    }

    public void setReportscore(float reportscore) {
        this.reportscore = reportscore;
    }

    public String getStureportanswer() {
        return stureportanswer;
    }

    public void setStureportanswer(String stureportanswer) {
        this.stureportanswer = stureportanswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "PScoreDto{" +
                "reportid=" + reportid +
                ", reportdescribe='" + reportdescribe + '\'' +
                ", reportscore=" + reportscore +
                ", stureportanswer='" + stureportanswer + '\'' +
                ", score=" + score +
                '}';
    }
}
