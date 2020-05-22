package com.coolwen.experimentplatform.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_effect")
public class Effect {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "effect_id")
    @TableGenerator(name = "effect_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;
    private String effect_name;
    private String effect_imgurl;
    private String effect_person;
    @Column(columnDefinition = "text")
    private String effect_content;
    private int dic_num;
    private Date dic_datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEffect_name() {
        return effect_name;
    }

    public void setEffect_name(String effect_name) {
        this.effect_name = effect_name;
    }

    public String getEffect_imgurl() {
        return effect_imgurl;
    }

    public void setEffect_imgurl(String effect_imgurl) {
        this.effect_imgurl = effect_imgurl;
    }

    public String getEffect_person() {
        return effect_person;
    }

    public void setEffect_person(String effect_person) {
        this.effect_person = effect_person;
    }

    public String getEffect_content() {
        return effect_content;
    }

    public void setEffect_content(String effect_content) {
        this.effect_content = effect_content;
    }

    public int getDic_num() {
        return dic_num;
    }

    public void setDic_num(int dic_num) {
        this.dic_num = dic_num;
    }

    public Date getDic_datetime() {
        return dic_datetime;
    }

    public void setDic_datetime(Date dic_datetime) {
        this.dic_datetime = dic_datetime;
    }

    @Override
    public String toString() {
        return "Effect{" +
                "id=" + id +
                ", effect_name='" + effect_name + '\'' +
                ", effect_imgurl='" + effect_imgurl + '\'' +
                ", effect_person='" + effect_person + '\'' +
                ", effect_content='" + effect_content + '\'' +
                ", dic_num=" + dic_num +
                ", dic_datetime=" + dic_datetime +
                '}';
    }
}
