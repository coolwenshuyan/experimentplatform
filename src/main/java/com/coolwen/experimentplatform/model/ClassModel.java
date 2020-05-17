package com.coolwen.experimentplatform.model;

import javax.persistence.*;
import java.util.List;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.model
 * @ClassName: ClassModel
 * @Author: Txc
 * @Description: 班级表
 * @Date: 2020/5/15 0015 14:33
 * @Version: 1.0
 */
@Entity
@Table(name = "t_class")
public class ClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "class_id")
    @TableGenerator(name = "class_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int classId;

    private String classCollage;//班级学院

    private String classMajor;//班级专业

    private String classGrade;//班级年级

    private String className;//班级名字

    private String classTeacher;//班级负责老师
    @Column(columnDefinition = "bit default 0")
    private boolean classIscurrent;//是否往届

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassCollage() {
        return classCollage;
    }

    public void setClassCollage(String classCollage) {
        this.classCollage = classCollage;
    }

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public boolean getClassIscurrent() {
        return classIscurrent;
    }

    public void setClassIscurrent(boolean classIscurrent) {
        this.classIscurrent = classIscurrent;
    }
}
