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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_kaohemodle_id")
    @TableGenerator(name = "t_kaohemodle_id", initialValue = 0, allocationSize = 1,table = "seq_table")

    private int stuId;
    private float mTestScore;
    private float mReportScore;
    private boolean mTeststate;
    private boolean mReportstate;
    private int mOrder;
    private float mScale;
    private float mScore;

    public KaoHeModelScore() {
    }

    public KaoHeModelScore(int stuId) {
        this.stuId = stuId;
    }

    public KaoHeModelScore(int stuId, float mTestScore, float mReportScore, boolean mTeststate, boolean mReportstate, int mOrder, float mScale, float mScore) {
        this.stuId = stuId;
        this.mTestScore = mTestScore;
        this.mReportScore = mReportScore;
        this.mTeststate = mTeststate;
        this.mReportstate = mReportstate;
        this.mOrder = mOrder;
        this.mScale = mScale;
        this.mScore = mScore;
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

    public boolean getmTeststate() {
        return mTeststate;
    }

    public void setmTeststate(boolean mTeststate) {
        this.mTeststate = mTeststate;
    }

    public boolean getmReportstate() {
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

    @Override
    public String toString() {
        return "KaoHeModleScore{" +
                "stuId=" + stuId +
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
