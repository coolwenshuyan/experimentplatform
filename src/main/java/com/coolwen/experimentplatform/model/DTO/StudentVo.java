package com.coolwen.experimentplatform.model.DTO;


public class StudentVo {
    private int id;

    private String stuUname;

    private String stuPassword;

    private String stuName;

    private String stuXuehao;

    private String stuMobile;

    private boolean stuCheckstate;

    private boolean stuIsinschool;

    private String className;

    public StudentVo(int id, String stuUname, String stuPassword, String stuName, String stuXuehao, String stuMobile, boolean stuCheckstate, boolean stuIsinschool, String className) {
        this.id = id;
        this.stuUname = stuUname;
        this.stuPassword = stuPassword;
        this.stuName = stuName;
        this.stuXuehao = stuXuehao;
        this.stuMobile = stuMobile;
        this.stuCheckstate = stuCheckstate;
        this.stuIsinschool = stuIsinschool;
        this.className = className;
    }

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
