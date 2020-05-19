package com.coolwen.experimentplatform.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "reply_id")
    @TableGenerator(name = "reply_id",initialValue = 0,allocationSize = 1,table = "seq_table")
    public int id;
    private int qid;            //问题id
    private String reply_pname; //回复用户名
    private String content;     //回复内容
    private Date dic_datetime;  //记录时间

    public Reply(int qid, String reply_pname, String content, Date dic_datetime) {
        this.qid = qid;
        this.reply_pname = reply_pname;
        this.content = content;
        this.dic_datetime = dic_datetime;
    }

    public Reply() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getReply_pname() {
        return reply_pname;
    }

    public void setReply_pname(String reply_pname) {
        this.reply_pname = reply_pname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDic_datetime() {
        return dic_datetime;
    }

    public void setDic_datetime(Date dic_datetime) {
        this.dic_datetime = dic_datetime;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", qid=" + qid +
                ", reply_pname='" + reply_pname + '\'' +
                ", content='" + content + '\'' +
                ", dic_datetime=" + dic_datetime +
                '}';
    }
}
