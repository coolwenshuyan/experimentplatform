package com.coolwen.experimentplatform.model;

import com.google.inject.internal.cglib.core.$Block;

import javax.persistence.*;


@Entity
@Table(name = "t_exp_model")
public class ExpModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "exp_model_id")
    @TableGenerator(name = "exp_model_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    @Column(name = "m_id")
    //模块id
    private int m_id;
    //模块名称
    @Column(length = 80,nullable = false)
    private String m_name;
    //实验负责人
    @Column(length = 30,nullable = false)
    private String m_manager;
    //实验类型
    @Column(length = 16,nullable = false)
    private String m_type;
    //课时
    private int classhour;
    //实验简介图片地址
    @Column(length = 150,nullable = false)
    private String imageurl;
    //实验介绍
    @Column(columnDefinition = "text")
    private String introduce;
    //实验目的
    @Column(length = 900)
    private String purpose;

    @Column(columnDefinition = "text")
    //实验原理
    private String principle;
    //实验内容
    @Column(columnDefinition = "text")
    private String m_content;
    //实验资料介绍
    @Column(columnDefinition = "text")
    private String m_edata_intro;
    //实验资料地址
    @Column(columnDefinition = "text")
    private String m_edataurl;
    //实验步骤
    @Column(columnDefinition = "longtext")
    private String m_step;
    //进入实验平台链接
    @Column(length = 300,nullable = false)
    private String m_inurl;
    //是否需要考核
    @Column(nullable = false,columnDefinition = "bit default 0")
    private boolean needKaohe;
    //模块报告类型
    @Column(nullable = false,columnDefinition = "bit default 0")
    private boolean report_type;


    @Override
    public String toString() {
        return "ExpModel{" +
                "m_id=" + m_id +
                ", m_name='" + m_name + '\'' +
                ", m_manager='" + m_manager + '\'' +
                ", m_type='" + m_type + '\'' +
                ", classhour=" + classhour +
                ", imageurl='" + imageurl + '\'' +
                ", introduce='" + introduce + '\'' +
                ", purpose='" + purpose + '\'' +
                ", principle='" + principle + '\'' +
                ", m_content='" + m_content + '\'' +
                ", m_edata_intro='" + m_edata_intro + '\'' +
                ", m_edataurl='" + m_edataurl + '\'' +
                ", m_step='" + m_step + '\'' +
                ", m_inurl='" + m_inurl + '\'' +
                ", needKaohe=" + needKaohe +
                ", report_type=" + report_type +
                '}';
    }

    public int getM_id() {
        return m_id;
    }

    public boolean isReport_type() {
        return report_type;
    }

    public void setReport_type(boolean report_type) {
        this.report_type = report_type;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_manager() {
        return m_manager;
    }

    public void setM_manager(String m_manager) {
        this.m_manager = m_manager;
    }

    public String getM_type() {
        return m_type;
    }

    public void setM_type(String m_type) {
        this.m_type = m_type;
    }

    public int getClasshour() {
        return classhour;
    }

    public void setClasshour(int classhour) {
        this.classhour = classhour;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    public String getM_content() {
        return m_content;
    }

    public void setM_content(String m_content) {
        this.m_content = m_content;
    }

    public String getM_edata_intro() {
        return m_edata_intro;
    }

    public void setM_edata_intro(String m_edata_intro) {
        this.m_edata_intro = m_edata_intro;
    }

    public String getM_edataurl() {
        return m_edataurl;
    }

    public void setM_edataurl(String m_edataurl) {
        this.m_edataurl = m_edataurl;
    }

    public String getM_step() {
        return m_step;
    }

    public void setM_step(String m_step) {
        this.m_step = m_step;
    }

    public String getM_inurl() {
        return m_inurl;
    }

    public void setM_inurl(String m_inurl) {
        this.m_inurl = m_inurl;
    }

    public boolean isNeedKaohe() {
        return needKaohe;
    }

    public void setNeedKaohe(boolean needKaohe) {
        this.needKaohe = needKaohe;
    }

}
