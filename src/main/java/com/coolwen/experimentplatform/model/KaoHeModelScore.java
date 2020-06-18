package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
@Entity
@Table(name = "t_kaohemodel_score")
public class KaoHeModelScore {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_kaohemodel_score")
    @TableGenerator(name = "t_kaohemodel_score", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;

    @Column(length = 11,nullable = false)
    private int tKaohemodleId;

    @Column(length = 11,nullable = false)
    private int stuId;

    @Column(nullable = false,columnDefinition = "int default 0")
    private float mTestScore;

    @Column(nullable = false,columnDefinition = "int default 0")
    private float mReportScore;

    @Column(nullable = false,columnDefinition="bit default 0")
    private boolean mTeststate;

    @Column(nullable = false,columnDefinition="bit default 0")
    private boolean mReportstate;

    @Column(nullable = false)
    private int mOrder;

    @Column(nullable = false)
    private float mScale;

    @Column(nullable = false,columnDefinition = "float default 0")
    private float mScore;

    @Column(nullable = false,columnDefinition="bit default 0")
    private boolean mReportteacherstate;

    public KaoHeModelScore() {
    }

    public KaoHeModelScore(int tKaohemodleId, int stuId, float mTestScore, float mReportScore, int mOrder, float mScale) {
        this.tKaohemodleId = tKaohemodleId;
        this.stuId = stuId;
        this.mTestScore = mTestScore;
        this.mReportScore = mReportScore;
        this.mOrder = mOrder;
        this.mScale = mScale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gettKaohemodleId() {
        return tKaohemodleId;
    }

    public void settKaohemodleId(int tKaohemodleId) {
        this.tKaohemodleId = tKaohemodleId;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public float getmTestScore() {
        return mTestScore;
    }

    public void setmTestScore(float mTestScore) {
        this.mTestScore = mTestScore;
    }

    public float getmReportScore() {
        return mReportScore;
    }

    public void setmReportScore(float mReportScore) {
        this.mReportScore = mReportScore;
    }

    public boolean ismTeststate() {
        return mTeststate;
    }

    public void setmTeststate(boolean mTeststate) {
        this.mTeststate = mTeststate;
    }

    public boolean ismReportstate() {
        return mReportstate;
    }

    public void setmReportstate(boolean mReportstate) {
        this.mReportstate = mReportstate;
    }

    public int getmOrder() {
        return mOrder;
    }

    public void setmOrder(int mOrder) {
        this.mOrder = mOrder;
    }

    public float getmScale() {
        return mScale;
    }

    public void setmScale(float mScale) {
        this.mScale = mScale;
    }

    public float getmScore() {
        return mScore;
    }

    public void setmScore(float mScore) {
        this.mScore = mScore;
    }

    public boolean ismReportteacherstate() {
        return mReportteacherstate;
    }

    public void setmReportteacherstate(boolean mReportteacherstate) {
        this.mReportteacherstate = mReportteacherstate;
    }

    @Override
    public String toString() {
        return "KaoHeModelScore{" +
                "id=" + id +
                ", tKaohemodleId=" + tKaohemodleId +
                ", stuId=" + stuId +
                ", mTestScore=" + mTestScore +
                ", mReportScore=" + mReportScore +
                ", mTeststate=" + mTeststate +
                ", mReportstate=" + mReportstate +
                ", mOrder=" + mOrder +
                ", mScale=" + mScale +
                ", mScore=" + mScore +
                '}';
    }
}
