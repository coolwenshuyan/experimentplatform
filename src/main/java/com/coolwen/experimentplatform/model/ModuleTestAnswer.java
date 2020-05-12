package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author 淮南
 * @date 2020/5/12 12:59
 */
@Entity
@Table(name = "t_mtest_answer")
public class ModuleTestAnswer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private int answerId;

    /**
     * 这个是答案的选项
     */
    @Column(name = "answer_describe")
    private String answerDescribe;

    /**
     * 选项的序号
     */
    @Column(name = "answer_order")
    private int answerOrder;

    @Column(name = "quest_id")
    private int questId;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerDescribe() {
        return answerDescribe;
    }

    public void setAnswerDescribe(String answerDescribe) {
        this.answerDescribe = answerDescribe;
    }

    public int getAnswerOrder() {
        return answerOrder;
    }

    public void setAnswerOrder(int answerOrder) {
        this.answerOrder = answerOrder;
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }


}
