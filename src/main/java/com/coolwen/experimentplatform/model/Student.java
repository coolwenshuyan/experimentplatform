package com.coolwen.experimentplatform.model;

import javax.persistence.*;


@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "student_id")
    @TableGenerator(name = "student_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    @Column(name = "id")
    private int id;
    //登陆账号
    @Column(name = "stu_uname")
    private String stuUname;
    //密码
    @Column(name = "stu_password")
    private String stuPassword;
    //学生姓名
    @Column(name = "stu_name")
    private String stuName;
    //学号
    @Column(name = "stu_xuehao")
    private String stuXuehao;
    //手机
    @Column(name = "stu_mobile")
    private String stuMobile;
    //账号状态
    @Column(name = "stu_checkstate")
    private boolean stuCheckstate;
    //是否本校
    @Column(name = "stu_isinschool")
    private boolean stuIsinschool;
    //所属班级id
    @Column(name = "class_id",columnDefinition = "int default 0")
    private int classId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuUname() {
        return stuUname;
    }

    public void setStuUname(String stuUname) {
        this.stuUname = stuUname;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuXuehao() {
        return stuXuehao;
    }

    public void setStuXuehao(String stuXuehao) {
        this.stuXuehao = stuXuehao;
    }

    public String getStuMobile() {
        return stuMobile;
    }

    public void setStuMobile(String stuMobile) {
        this.stuMobile = stuMobile;
    }

    public boolean isStuCheckstate() {
        return stuCheckstate;
    }

    public void setStuCheckstate(boolean stuCheckstate) {
        this.stuCheckstate = stuCheckstate;
    }

    public boolean isStuIsinschool() {
        return stuIsinschool;
    }

    public void setStuIsinschool(boolean stuIsinschool) {
        this.stuIsinschool = stuIsinschool;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuUname='" + stuUname + '\'' +
                ", stuPassword='" + stuPassword + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuXuehao='" + stuXuehao + '\'' +
                ", stuMobile='" + stuMobile + '\'' +
                ", stuCheckstate=" + stuCheckstate +
                ", stuIsinschool=" + stuIsinschool +
                ", classId=" + classId +
                '}';
    }
}
