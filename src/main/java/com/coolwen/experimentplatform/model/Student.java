package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author 淮南
 * @date 2020/5/13 20:07
 */
@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "student_id")
    @TableGenerator(name = "student_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    @Column(name = "id")
    private int id;

    @Column(name = "stu_uname")
    private String stuUname;

    @Column(name = "stu_password")
    private String stuPassword;

    @Column(name = "stu_name")
    private float stuName;

    @Column(name = "stu_xuehao")
    private int stuXuehao;

    @Column(name = "stu_mobile")
    private int stuMobile;

    @Column(name = "stu_checkstate")
    private int stuCheckstate;

    @Column(name = "stu_isinschool")
    private int stuIsinschool;

    @Column(name = "class_id")
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

    public float getStuName() {
        return stuName;
    }

    public void setStuName(float stuName) {
        this.stuName = stuName;
    }

    public int getStuXuehao() {
        return stuXuehao;
    }

    public void setStuXuehao(int stuXuehao) {
        this.stuXuehao = stuXuehao;
    }

    public int getStuMobile() {
        return stuMobile;
    }

    public void setStuMobile(int stuMobile) {
        this.stuMobile = stuMobile;
    }

    public int getStuCheckstate() {
        return stuCheckstate;
    }

    public void setStuCheckstate(int stuCheckstate) {
        this.stuCheckstate = stuCheckstate;
    }

    public int getStuIsinschool() {
        return stuIsinschool;
    }

    public void setStuIsinschool(int stuIsinschool) {
        this.stuIsinschool = stuIsinschool;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
