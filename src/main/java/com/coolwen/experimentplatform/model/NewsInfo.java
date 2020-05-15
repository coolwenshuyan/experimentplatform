package com.coolwen.experimentplatform.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_newsinfo")
public class NewsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "newsinfo_id")
    @TableGenerator(name = "newsinfo_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;

    private String news_name;

    private String news_person;

    private String content;

    private int dic_num;

    private Date dic_datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }

    public String getNews_person() {
        return news_person;
    }

    public void setNews_person(String news_person) {
        this.news_person = news_person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
