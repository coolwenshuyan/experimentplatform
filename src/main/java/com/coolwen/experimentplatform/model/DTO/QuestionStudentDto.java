package com.coolwen.experimentplatform.model.DTO;

import java.util.Date;

/**
 * 建立一个通过用户名查id的DTO
 *  @author yellow
 */

public class QuestionStudentDto {

    private int id;   //t_question   id
    private int sid;  //学生id
    private String content;  //问题
    private Date dic_datetime;  //时间
    private String stu_uname;   //学生姓名
    private Boolean isreply;    //是否回复

    public QuestionStudentDto(int id, int sid, String content, Date dic_datetime, String stu_uname, Boolean isreply) {
        this.id = id;
        this.sid = sid;
        this.content = content;
        this.dic_datetime = dic_datetime;
        this.stu_uname = stu_uname;
        this.isreply = isreply;
    }

    public QuestionStudentDto() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public String getStu_uname() {
        return stu_uname;
    }

    public void setStu_uname(String stu_uname) {
        this.stu_uname = stu_uname;
    }

    public Boolean getIsreply() {
        return isreply;
    }

    public void setIsreply(Boolean isreply) {
        this.isreply = isreply;
    }

    @Override
    public String toString() {
        return "QuestionStudentDto{" +
                "id=" + id +
                ", sid=" + sid +
                ", content='" + content + '\'' +
                ", dic_datetime=" + dic_datetime +
                ", stu_uname='" + stu_uname + '\'' +
                ", isreply=" + isreply +
                '}';
    }
}
