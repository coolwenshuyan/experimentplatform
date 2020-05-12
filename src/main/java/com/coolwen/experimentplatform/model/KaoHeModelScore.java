package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 15:34
 * @Description: TODO
 */
@Entity
@Table(name = "t_kaohemodel_score")
public class KaoHeModelScore {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_kaohemodle_id")
    @TableGenerator(name = "t_kaohemodle_id", initialValue = 0, allocationSize = 1,table = "t_kaohemodel_score")

    private int stuId;
    private float mTestScore;
    private float mReportScore;
    private byte mTeststate;
    private byte mReportstate;
    private int mOrder;
    private float mScale;
    private float mScore;

    public KaoHeModelScore() {
    }

    public KaoHeModelScore(int stuId, float mTestScore, float mReportScore, byte mTeststate, byte mReportstate, int mOrder, float mScale, float mScore) {
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

    public byte getmTeststate() {
        return mTeststate;
    }

    public void setmTeststate(byte mTeststate) {
        this.mTeststate = mTeststate;
    }

    public byte getmReportstate() {
        return mReportstate;
    }

    public void setmReportstate(byte mReportstate) {
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
