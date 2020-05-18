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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_totalscore_current")
    @TableGenerator(name = "t_totalscore_current", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;

    @Column(length = 11,nullable = false)
    private int stuId;

    @Column(length = 11,nullable = false)
    private int kaoheNum;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float mTotalScore;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float testScore;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float totalScore;

    public TotalScoreCurrent() {
    }


    public TotalScoreCurrent(int stuId, int kaoheNum) {
        this.stuId = stuId;
        this.kaoheNum = kaoheNum;
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
        return "TotalScoreCurrent{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", kaoheNum=" + kaoheNum +
                ", mTotalScore=" + mTotalScore +
                ", testScore=" + testScore +
                ", totalScore=" + totalScore +
                '}';
    }
}
