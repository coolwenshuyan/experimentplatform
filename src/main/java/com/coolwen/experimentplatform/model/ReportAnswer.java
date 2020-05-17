package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author 淮南
 * @date 2020/5/13 21:04
 */
@Entity
@Table(name = "t_mreport_answer")
public class ReportAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "mreport_answer_id")
    @TableGenerator(name = "mreport_answer_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;

    @Column(name = "stu_id")
    private int stuId;

    @Column(name = "report_id")
    private int reportId;

    @Column(name = "stu_report_answer")
    private Long stuReportAnswer;

    @Column(name = "score",columnDefinition = "int default 0")
    private int score;

    public ReportAnswer(int stuId, int reportId, Long stuReportAnswer, int score) {
        this.stuId = stuId;
        this.reportId = reportId;
        this.stuReportAnswer = stuReportAnswer;
        this.score = score;
    }

    public ReportAnswer() {
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

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Long getStuReportAnswer() {
        return stuReportAnswer;
    }

    public void setStuReportAnswer(Long stuReportAnswer) {
        this.stuReportAnswer = stuReportAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ReportAnswer{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", reportId=" + reportId +
                ", stuReportAnswer=" + stuReportAnswer +
                ", score=" + score +
                '}';
    }
}
