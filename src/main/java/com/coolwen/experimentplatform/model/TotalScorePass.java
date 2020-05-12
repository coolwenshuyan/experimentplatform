package com.coolwen.experimentplatform.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author Artell
 * @version 2020/5/12 18:27
 */

@Entity
@Table(name = "t_totalscore_current")
public class TotalScorePass {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_totalscore_current")
    @TableGenerator(name = "t_totalscore_current", initialValue = 0, allocationSize = 1, table = "seq_table")
    private int id;
    private int stuId;
    private int kaoHeNum;
    private String KaoHeName;
    private String KaoHeMtestScore;
    private String KaoHeMreportScore;
    private String KaoHeMtestScoreBaifengbi;
    private String KaoHeMreportScoreBaifengbi;
    private String KaoHeMscale;
    private float testScore;
    private float testBaiFenBi;
    private float kaoHeBaiFenBi;
    private float totalScore;
    private Date finalDatetime;

    public TotalScorePass() {
    }

    public TotalScorePass(int id, int stuId, int kaoHeNum, String kaoHeName, String kaoHeMtestScore, String kaoHeMreportScore, String kaoHeMtestScoreBaifengbi, String kaoHeMreportScoreBaifengbi, String kaoHeMscale, float testScore, float testBaiFenBi, float kaoHeBaiFenBi, float totalScore, Date finalDatetime) {
        this.id = id;
        this.stuId = stuId;
        this.kaoHeNum = kaoHeNum;
        KaoHeName = kaoHeName;
        KaoHeMtestScore = kaoHeMtestScore;
        KaoHeMreportScore = kaoHeMreportScore;
        KaoHeMtestScoreBaifengbi = kaoHeMtestScoreBaifengbi;
        KaoHeMreportScoreBaifengbi = kaoHeMreportScoreBaifengbi;
        KaoHeMscale = kaoHeMscale;
        this.testScore = testScore;
        this.testBaiFenBi = testBaiFenBi;
        this.kaoHeBaiFenBi = kaoHeBaiFenBi;
        this.totalScore = totalScore;
        this.finalDatetime = finalDatetime;
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

    public int getKaoHeNum() {
        return kaoHeNum;
    }

    public void setKaoHeNum(int kaoHeNum) {
        this.kaoHeNum = kaoHeNum;
    }

    public String getKaoHeName() {
        return KaoHeName;
    }

    public void setKaoHeName(String kaoHeName) {
        KaoHeName = kaoHeName;
    }

    public String getKaoHeMtestScore() {
        return KaoHeMtestScore;
    }

    public void setKaoHeMtestScore(String kaoHeMtestScore) {
        KaoHeMtestScore = kaoHeMtestScore;
    }

    public String getKaoHeMreportScore() {
        return KaoHeMreportScore;
    }

    public void setKaoHeMreportScore(String kaoHeMreportScore) {
        KaoHeMreportScore = kaoHeMreportScore;
    }

    public String getKaoHeMtestScoreBaifengbi() {
        return KaoHeMtestScoreBaifengbi;
    }

    public void setKaoHeMtestScoreBaifengbi(String kaoHeMtestScoreBaifengbi) {
        KaoHeMtestScoreBaifengbi = kaoHeMtestScoreBaifengbi;
    }

    public String getKaoHeMreportScoreBaifengbi() {
        return KaoHeMreportScoreBaifengbi;
    }

    public void setKaoHeMreportScoreBaifengbi(String kaoHeMreportScoreBaifengbi) {
        KaoHeMreportScoreBaifengbi = kaoHeMreportScoreBaifengbi;
    }

    public String getKaoHeMscale() {
        return KaoHeMscale;
    }

    public void setKaoHeMscale(String kaoHeMscale) {
        KaoHeMscale = kaoHeMscale;
    }

    public float getTestScore() {
        return testScore;
    }

    public void setTestScore(float testScore) {
        this.testScore = testScore;
    }

    public float getTestBaiFenBi() {
        return testBaiFenBi;
    }

    public void setTestBaiFenBi(float testBaiFenBi) {
        this.testBaiFenBi = testBaiFenBi;
    }

    public float getKaoHeBaiFenBi() {
        return kaoHeBaiFenBi;
    }

    public void setKaoHeBaiFenBi(float kaoHeBaiFenBi) {
        this.kaoHeBaiFenBi = kaoHeBaiFenBi;
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
                ", kaoHeNum=" + kaoHeNum +
                ", KaoHeName='" + KaoHeName + '\'' +
                ", KaoHeMtestScore='" + KaoHeMtestScore + '\'' +
                ", KaoHeMreportScore='" + KaoHeMreportScore + '\'' +
                ", KaoHeMtestScoreBaifengbi='" + KaoHeMtestScoreBaifengbi + '\'' +
                ", KaoHeMreportScoreBaifengbi='" + KaoHeMreportScoreBaifengbi + '\'' +
                ", KaoHeMscale='" + KaoHeMscale + '\'' +
                ", testScore=" + testScore +
                ", testBaiFenBi=" + testBaiFenBi +
                ", kaoHeBaiFenBi=" + kaoHeBaiFenBi +
                ", totalScore=" + totalScore +
                ", finalDatetime=" + finalDatetime +
                '}';
    }
}
