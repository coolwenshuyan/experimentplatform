package com.coolwen.experimentplatform.model;

import javax.persistence.*;

@Entity
@Table(name = "t_kaohemodel")
public class KaoheModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //模块id
    private int m_id;
    //考核模块序号
    private int m_order;
    //实验名称
    private String Experiment_name;
    //课时
    private int class_hour;
    //实验目的
    private String shiyan_Purpose;
    //实验类型
    private String shiyan_Types;
    //总成绩中该模块的分值权重
    private float m_scale;
    //模块测试占比
    private float m_test_baifenbi;
    //模块报告占比
    private float m_report_baifenbi;

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

    public String getShiyan_Purpose() {
        return shiyan_Purpose;
    }

    public void setShiyan_Purpose(String shiyan_Purpose) {
        this.shiyan_Purpose = shiyan_Purpose;
    }

    public String getShiyan_Types() {
        return shiyan_Types;
    }

    public void setShiyan_Types(String shiyan_Types) {
        this.shiyan_Types = shiyan_Types;
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

    public String getExperiment_name() {
        return Experiment_name;
    }

    public void setExperiment_name(String experiment_name) {
        Experiment_name = experiment_name;
    }

    @Override
    public String toString() {
        return "KaoheModel{" +
                "id=" + id +
                ", m_id=" + m_id +
                ", m_order=" + m_order +
                ", Experiment_name='" + Experiment_name + '\'' +
                ", class_hour='" + class_hour + '\'' +
                ", shiyan_Purpose='" + shiyan_Purpose + '\'' +
                ", shiyan_Types='" + shiyan_Types + '\'' +
                ", m_scale=" + m_scale +
                ", m_test_baifenbi=" + m_test_baifenbi +
                ", m_report_baifenbi=" + m_report_baifenbi +
                '}';
    }

    public int getClass_hour() {
        return class_hour;
    }

    public void setClass_hour(int class_hour) {
        this.class_hour = class_hour;
    }

}
