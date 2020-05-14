package com.coolwen.experimentplatform.model;

import javax.persistence.*;

@Entity
@Table(name = "t_setinfo")
public class SetInfo {

    @Id //自动获取id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "setinfo_id")
    @TableGenerator(name = "setinfo_id", initialValue = 0, allocationSize = 1, table = "seq_table")
    private int id;

    private String set_platintro;

    private String set_aboutus;

    private String set_platstep;

    private String set_rotateimg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSet_platintro() {
        return set_platintro;
    }

    public void setSet_platintro(String set_platintro) {
        this.set_platintro = set_platintro;
    }

    public String getSet_aboutus() {
        return set_aboutus;
    }

    public void setSet_aboutus(String set_aboutus) {
        this.set_aboutus = set_aboutus;
    }

    public String getSet_platstep() {
        return set_platstep;
    }

    public void setSet_platstep(String set_platstep) {
        this.set_platstep = set_platstep;
    }

    public String getSet_rotateimg() {
        return set_rotateimg;
    }

    public void setSet_rotateimg(String set_rotateimg) {
        this.set_rotateimg = set_rotateimg;
    }

    @Override
    public String toString() {
        return "SetInfo{" +
                "id=" + id +
                ", set_platintro='" + set_platintro + '\'' +
                ", set_aboutus='" + set_aboutus + '\'' +
                ", set_platstep='" + set_platstep + '\'' +
                ", set_rotateimg='" + set_rotateimg + '\'' +
                '}';
    }
}
