package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author 淮南
 * @date 2020/5/12 11:42
 */
@Entity
@Table(name = "t_mtest_quest")
public class ModuleTestQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "mtest_quest_id")
    @TableGenerator(name = "mtest_quest_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    @Column(name = "quest_id")
    private int questId;

    @Column(name = "quest_describe")
    private String questDescribe;

    @Column(name = "quest_type")
    private String questType;

    @Column(name = "quest_answer")
    private String questAnswer;

    @Column(name = "quest_score")
    private float questScore;

    @Column(name = "quest_order")
    private int questOrder;

    @Column(name = "m_id")
    private int mId;


    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public String getQuestDescribe() {
        return questDescribe;
    }

    public void setQuestDescribe(String questDescribe) {
        this.questDescribe = questDescribe;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getQuestAnswer() {
        return questAnswer;
    }

    public void setQuestAnswer(String questAnswer) {
        this.questAnswer = questAnswer;
    }

    public float getQuestScore() {
        return questScore;
    }

    public void setQuestScore(float questScore) {
        this.questScore = questScore;
    }

    public int getQuestOrder() {
        return questOrder;
    }

    public void setQuestOrder(int questOrder) {
        this.questOrder = questOrder;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
