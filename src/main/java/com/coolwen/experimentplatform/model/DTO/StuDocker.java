package com.coolwen.experimentplatform.model.DTO;

import java.util.Date;

public class StuDocker {

    private String stuXuehao;

    private String stuName;

    private String dc_url;

    private String dc_start_datetime;

    private String dc_end_datetime;

    public String getStuXuehao() {
        return stuXuehao;
    }

    public void setStuXuehao(String stuXuehao) {
        this.stuXuehao = stuXuehao;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getDc_url() {
        return dc_url;
    }

    public void setDc_url(String dc_url) {
        this.dc_url = dc_url;
    }

    public String getDc_start_datetime() {
        return dc_start_datetime;
    }

    public void setDc_start_datetime(String dc_start_datetime) {
        this.dc_start_datetime = dc_start_datetime;
    }

    public String getDc_end_datetime() {
        return dc_end_datetime;
    }

    public void setDc_end_datetime(String dc_end_datetime) {
        this.dc_end_datetime = dc_end_datetime;
    }
}
