package com.coolwen.experimentplatform.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_docker")
public class Docker {
    @Id //自动获取id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "docker_id")
    @TableGenerator(name = "docker_id", initialValue = 0, allocationSize = 1, table = "seq_table")
    private int id;
    //学生id
    @Column(name = "stu_id",columnDefinition = "int default 0")
    private int stu_id;
    //docker地址
    @Column(name = "dc_url",nullable = false)
    private String dc_url;
    //学生使用时间
    @Column(name = "dc_start_datetime")
    private Date dc_start_datetime;
    //学生使用结束时间
    @Column(name = "dc_end_datetime")
    private Date dc_end_datetime;
    //docker地址使用状态
    @Column(name = "dc_state",nullable = false,columnDefinition = "bit default 0")
    private boolean dc_state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getDc_url() {
        return dc_url;
    }

    public void setDc_url(String dc_url) {
        this.dc_url = dc_url;
    }

    public Date getDc_start_datetime() {
        return dc_start_datetime;
    }

    public void setDc_start_datetime(Date dc_start_datetime) {
        this.dc_start_datetime = dc_start_datetime;
    }

    public Date getDc_end_datetime() {
        return dc_end_datetime;
    }

    public void setDc_end_datetime(Date dc_end_datetime) {
        this.dc_end_datetime = dc_end_datetime;
    }

    public boolean isDc_state() {
        return dc_state;
    }

    public void setDc_state(boolean dc_state) {
        this.dc_state = dc_state;
    }

    @Override
    public String toString() {
        return "Docker{" +
                "id=" + id +
                ", stu_id=" + stu_id +
                ", dc_url='" + dc_url + '\'' +
                ", dc_start_datetime=" + dc_start_datetime +
                ", dc_end_datetime=" + dc_end_datetime +
                ", dc_state=" + dc_state +
                '}';
    }
}
