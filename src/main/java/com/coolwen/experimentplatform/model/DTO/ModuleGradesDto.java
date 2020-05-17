package com.coolwen.experimentplatform.model.DTO;

public class ModuleGradesDto {

    private int m_id;

    private String m_name;

    private float m_test_score;

    private float m_report_score;

    private float m_score;

    public ModuleGradesDto(int m_id, String m_name, float m_test_score, float m_report_score, float m_score) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_test_score = m_test_score;
        this.m_report_score = m_report_score;
        this.m_score = m_score;
    }

    public int getM_id() {
        return m_id;
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

    public float getM_score() {
        return m_score;
    }

    public void setM_score(float m_score) {
        this.m_score = m_score;
    }
}
