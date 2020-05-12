package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 15:34
 * @Description: TODO
 */
@Entity
@Table(name = "t_kaohemodel_score")
public class KaoHeModleScore {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_kaohemodle_id")
    @TableGenerator(name = "t_kaohemodle_id", initialValue = 0, allocationSize = 1,table = "t_kaohemodel_score")

    private int stu_id;
    private float m_test_score;
    private float m_report_score;
    private byte m_teststate;
    private byte m_reportstate;
    private int m_order;
    private float m_scale;
    private float m_score;

    public KaoHeModleScore() {
    }

    public KaoHeModleScore(int stu_id, float m_test_score, float m_report_score, byte m_teststate, byte m_reportstate, int m_order, float m_scale, float m_score) {
        this.stu_id = stu_id;
        this.m_test_score = m_test_score;
        this.m_report_score = m_report_score;
        this.m_teststate = m_teststate;
        this.m_reportstate = m_reportstate;
        this.m_order = m_order;
        this.m_scale = m_scale;
        this.m_score = m_score;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public float getM_test_score() {
        return m_test_score;
    }

    public void setM_test_score(float m_test_score) {
        this.m_test_score = m_test_score;
    }

    public float getM_report_score() {
        return m_report_score;
    }

    public void setM_report_score(float m_report_score) {
        this.m_report_score = m_report_score;
    }

    public byte getM_teststate() {
        return m_teststate;
    }

    public void setM_teststate(byte m_teststate) {
        this.m_teststate = m_teststate;
    }

    public byte getM_reportstate() {
        return m_reportstate;
    }

    public void setM_reportstate(byte m_reportstate) {
        this.m_reportstate = m_reportstate;
    }

    public int getM_order() {
        return m_order;
    }

    public void setM_order(int m_order) {
        this.m_order = m_order;
    }

    public float getM_scale() {
        return m_scale;
    }

    public void setM_scale(float m_scale) {
        this.m_scale = m_scale;
    }

    public float getM_score() {
        return m_score;
    }

    public void setM_score(float m_score) {
        this.m_score = m_score;
    }

    @Override
    public String toString() {
        return "KaoHeModleScore{" +
                "stu_id=" + stu_id +
                ", m_test_score=" + m_test_score +
                ", m_report_score=" + m_report_score +
                ", m_teststate=" + m_teststate +
                ", m_reportstate=" + m_reportstate +
                ", m_order=" + m_order +
                ", m_scale=" + m_scale +
                ", m_score=" + m_score +
                '}';
    }
}
