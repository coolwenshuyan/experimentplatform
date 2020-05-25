package com.coolwen.experimentplatform.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Artell
 * @version 2020/5/12 18:27
 */

@Entity
@Table(name = "t_totalscore_pass")
public class TotalScorePass {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_totalscore_pass")
    @TableGenerator(name = "t_totalscore_pass", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;

    @Column(length = 11,nullable = false)
    private int stuId;

    @Column(nullable = false)
    private int kaoheNum;

    @Column(columnDefinition = "text")
    private String kaoheName;

    @Column(columnDefinition = "text")
    private String kaoheMtestscore;

    @Column(columnDefinition = "text")
    private String kaoheMreportscore;

    @Column(columnDefinition = "text")
    private String kaoheMtestscoreBaifengbi;

    @Column(columnDefinition = "text")
    private String kaoheMreportscoreBaifengbi;

    @Column(columnDefinition = "text")
    private String kaoheMscale;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float mTotalScore;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float testScore;

    @Column(nullable = false)
    private float testBaifenbi;

    @Column(nullable = false)
    private float kaoheBaifenbi;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float totalScore;

    @Column(nullable = false)
    private Date finalDatetime;

    public TotalScorePass() {
    }

    public TotalScorePass(int stuId, int kaoheNum, String kaoheName, String kaoheMtestscore, String kaoheMreportscore, String kaoheMtestscoreBaifengbi, String kaoheMreportscoreBaifengbi, String kaoheMscale, float testScore, float testBaifenbi, float kaoheBaifenbi, float totalScore, Date finalDatetime) {
        this.stuId = stuId;
        this.kaoheNum = kaoheNum;
        this.kaoheName = kaoheName;
        this.kaoheMtestscore = kaoheMtestscore;
        this.kaoheMreportscore = kaoheMreportscore;
        this.kaoheMtestscoreBaifengbi = kaoheMtestscoreBaifengbi;
        this.kaoheMreportscoreBaifengbi = kaoheMreportscoreBaifengbi;
        this.kaoheMscale = kaoheMscale;
        this.testScore = testScore;
        this.testBaifenbi = testBaifenbi;
        this.kaoheBaifenbi = kaoheBaifenbi;
        this.totalScore = totalScore;
        this.finalDatetime = finalDatetime;
    }

    public float getmTotalScore() {
        return mTotalScore;
    }

    public void setmTotalScore(float mTotalScore) {
        this.mTotalScore = mTotalScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getKaoheNum() {
        return kaoheNum;
    }

    public void setKaoheNum(int kaoheNum) {
        this.kaoheNum = kaoheNum;
    }

    public String getKaoheName() {
        return kaoheName;
    }

    public void setKaoheName(String kaoheName) {
        this.kaoheName = kaoheName;
    }

    public String getKaoheMtestscore() {
        return kaoheMtestscore;
    }

    public void setKaoheMtestscore(String kaoheMtestscore) {
        this.kaoheMtestscore = kaoheMtestscore;
    }

    public String getKaoheMreportscore() {
        return kaoheMreportscore;
    }

    public void setKaoheMreportscore(String kaoheMreportscore) {
        this.kaoheMreportscore = kaoheMreportscore;
    }

    public String getKaoheMtestscoreBaifengbi() {
        return kaoheMtestscoreBaifengbi;
    }

    public void setKaoheMtestscoreBaifengbi(String kaoheMtestscoreBaifengbi) {
        this.kaoheMtestscoreBaifengbi = kaoheMtestscoreBaifengbi;
    }

    public String getKaoheMreportscoreBaifengbi() {
        return kaoheMreportscoreBaifengbi;
    }

    public void setKaoheMreportscoreBaifengbi(String kaoheMreportscoreBaifengbi) {
        this.kaoheMreportscoreBaifengbi = kaoheMreportscoreBaifengbi;
    }

    public String getKaoheMscale() {
        return kaoheMscale;
    }

    public void setKaoheMscale(String kaoheMscale) {
        this.kaoheMscale = kaoheMscale;
    }

    public float getTestScore() {
        return testScore;
    }

    public void setTestScore(float testScore) {
        this.testScore = testScore;
    }

    public float getTestBaifenbi() {
        return testBaifenbi;
    }

    public void setTestBaifenbi(float testBaifenbi) {
        this.testBaifenbi = testBaifenbi;
    }

    public float getKaoheBaifenbi() {
        return kaoheBaifenbi;
    }

    public void setKaoheBaifenbi(float kaoheBaifenbi) {
        this.kaoheBaifenbi = kaoheBaifenbi;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public Date getFinalDatetime() {
        return finalDatetime;
    }

    public void setFinalDatetime(Date finalDatetime) {
        this.finalDatetime = finalDatetime;
    }

    @Override
    public String toString() {
        return "TotalScorePass{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", kaoheNum=" + kaoheNum +
                ", kaoheName='" + kaoheName + '\'' +
                ", kaoheMtestscore='" + kaoheMtestscore + '\'' +
                ", kaoheMreportscore='" + kaoheMreportscore + '\'' +
                ", kaoheMtestscoreBaifengbi='" + kaoheMtestscoreBaifengbi + '\'' +
                ", kaoheMreportscoreBaifengbi='" + kaoheMreportscoreBaifengbi + '\'' +
                ", kaoheMscale='" + kaoheMscale + '\'' +
                ", testScore=" + testScore +
                ", testBaifenbi=" + testBaifenbi +
                ", kaoheBaifenbi=" + kaoheBaifenbi +
                ", totalScore=" + totalScore +
                ", finalDatetime=" + finalDatetime +
                '}';
    }
}
