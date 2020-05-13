package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */

@Entity
@Table(name = "t_totalscore_current")
public class TotalScoreCurrent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 11,nullable = false)
    private int stuId;

    @Column(length = 11,nullable = false)
    private int kaoheNum;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float testScore;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float totalScore;

    public TotalScoreCurrent() {
    }


    public TotalScoreCurrent(int stuId, int kaoheNum, float testScore, float totalScore) {
        this.stuId = stuId;
        this.kaoheNum = kaoheNum;
        this.testScore = testScore;
        this.totalScore = totalScore;
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
        return "TotalScoreCurrent{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", kaoheNum=" + kaoheNum +
                ", testScore=" + testScore +
                ", totalScore=" + totalScore +
                '}';
    }
}
