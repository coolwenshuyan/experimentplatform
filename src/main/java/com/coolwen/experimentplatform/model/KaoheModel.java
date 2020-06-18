package com.coolwen.experimentplatform.model;

import javax.persistence.*;

@Entity
@Table(name = "t_kaohemodel")
public class KaoheModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "t_kaohemodel")
    @TableGenerator(name = "t_kaohemodel", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;
    //模块id
    @Column(nullable = false)
    private int m_id;
    //考核模块序号
    @Column(nullable = false)
    private int m_order;
    //总成绩中该模块的分值权重
    @Column(nullable = false)
    private float m_scale;
    //模块测试占比
    @Column(nullable = false)
    private float m_test_baifenbi;
    //模块报告占比
    @Column(nullable = false)
    private float m_report_baifenbi;

    @Column(nullable = false)
    private float test_baifenbi;

    @Column(nullable = false)
    private float kaohe_baifenbi;



    public KaoheModel(int id, int m_id, int m_order, float m_scale, float m_test_baifenbi, float m_report_baifenbi, float test_baifenbi, float kaohe_baifenbi) {
        this.id = id;
        this.m_id = m_id;
        this.m_order = m_order;
        this.m_scale = m_scale;
        this.m_test_baifenbi = m_test_baifenbi;
        this.m_report_baifenbi = m_report_baifenbi;
        this.test_baifenbi = test_baifenbi;
        this.kaohe_baifenbi = kaohe_baifenbi;
    }

    public KaoheModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
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

    public float getM_test_baifenbi() {
        return m_test_baifenbi;
    }

    public void setM_test_baifenbi(float m_test_baifenbi) {
        this.m_test_baifenbi = m_test_baifenbi;
    }

    public float getM_report_baifenbi() {
        return m_report_baifenbi;
    }

    public void setM_report_baifenbi(float m_report_baifenbi) {
        this.m_report_baifenbi = m_report_baifenbi;
    }

    public float getTest_baifenbi() {
        return test_baifenbi;
    }

    public void setTest_baifenbi(float test_baifenbi) {
        this.test_baifenbi = test_baifenbi;
    }

    public float getKaohe_baifenbi() {
        return kaohe_baifenbi;
    }

    public void setKaohe_baifenbi(float kaohe_baifenbi) {
        this.kaohe_baifenbi = kaohe_baifenbi;
    }

    @Override
    public String toString() {
        return "KaoheModel{" +
                "id=" + id +
                ", m_id=" + m_id +
                ", m_order=" + m_order +
                ", m_scale=" + m_scale +
                ", m_test_baifenbi=" + m_test_baifenbi +
                ", m_report_baifenbi=" + m_report_baifenbi +
                ", test_baifenbi=" + test_baifenbi +
                ", kaohe_baifenbi=" + kaohe_baifenbi +
                '}';
    }
}
