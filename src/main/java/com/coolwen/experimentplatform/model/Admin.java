package com.coolwen.experimentplatform.model;

import javax.persistence.*;


@Entity
@Table(name = "t_admin")
public class Admin {
    @Id //自动获取id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_id")
    @TableGenerator(name = "admin_id", initialValue = 0, allocationSize = 1, table = "seq_table")
    private int id;
    private String uname;
    private String password;
    private String mobile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}

