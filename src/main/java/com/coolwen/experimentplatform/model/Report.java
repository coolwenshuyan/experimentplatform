package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author 淮南
 * @date 2020/5/13 20:07
 */
@Entity
@Table(name = "t_mreport")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "mreport_id")
    @TableGenerator(name = "mreport_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    @Column(name = "report_id")
    private int reportId;

    @Column(name = "report_order")
    private int reportOrder;

    @Column(name = "report_describe")
    private String reportDescribe;

    @Column(name = "m_id")
    private int mId;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "report_score")
    private float reportScore;


    public Report(int reportOrder, String reportDescribe, int mId, String reportType, float reportScore) {
        this.reportOrder = reportOrder;
        this.reportDescribe = reportDescribe;
        this.mId = mId;
        this.reportType = reportType;
        this.reportScore = reportScore;
    }

    public Report() {
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportDescribe() {
        return reportDescribe;
    }

    public void setReportDescribe(String reportDescribe) {
        this.reportDescribe = reportDescribe;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public float getReportScore() {
        return reportScore;
    }

    public void setReportScore(float reportScore) {
        this.reportScore = reportScore;
    }

    public int getReportOrder() {
        return reportOrder;
    }

    public void setReportOrder(int reportOrder) {
        this.reportOrder = reportOrder;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportDescribe='" + reportDescribe + '\'' +
                ", reportType='" + reportType + '\'' +
                ", reportScore=" + reportScore +
                ", reportOrder=" + reportOrder +
                ", mId=" + mId +
                '}';
    }
}
