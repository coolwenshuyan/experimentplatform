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
    @TableGenerator(name = "t_totalscore_current", initialValue = 0, allocationSize = 1, table = "seq_table")
    private int id;
    private int stuId;
    private int kaoHeNum;
    private float testScore;
    private float totalScore;

    public TotalScoreCurrent() {
    }

    public TotalScoreCurrent(int id, int stuId, int kaoHeNum, float testScore, float totalScore) {
        this.id = id;
        this.stuId = stuId;
        this.kaoHeNum = kaoHeNum;
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

    public int getKaoHeNum() {
        return kaoHeNum;
    }

    public void setKaoHeNum(int kaoHeNum) {
        this.kaoHeNum = kaoHeNum;
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
        return "TotalscoreCurrent{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", kaoHeNum=" + kaoHeNum +
                ", testScore=" + testScore +
                ", totalScore=" + totalScore +
                '}';
    }
}
