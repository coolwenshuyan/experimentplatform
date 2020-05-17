package com.coolwen.experimentplatform.model;


import javax.persistence.*;

@Entity
@Table(name = "t_mtest_answer_stu")
public class ModuleTestAnswerStu {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "mtest_answer_stu_id")
    @TableGenerator(name = "mtest_answer_stu_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;

    private int stu_id;

    private int  quest_id;
    @Column(length = 50)
    private String stu_quest_answer;
    @Column(columnDefinition = "int default 0")
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(int quest_id) {
        this.quest_id = quest_id;
    }

    public String getStu_quest_answer() {
        return stu_quest_answer;
    }

    public void setStu_quest_answer(String stu_quest_answer) {
        this.stu_quest_answer = stu_quest_answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
