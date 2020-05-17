package com.coolwen.experimentplatform.model.DTO;

/**
 * @author CoolWen
 * @version 2020-05-17 15:47
 */
public class QuestAnswerDto {
    private int questId;

    private String questDescribe;

    private String questAnswer;

    private float questScore;

    private String questType;

    private int questOrder;

    private int answerId;

    private String answerDescribe;

    private int answerOrder;

    public QuestAnswerDto(int questId, String questDescribe, String questAnswer, float questScore, String questType, int questOrder, int answerId, String answerDescribe, int answerOrder) {
        this.questId = questId;
        this.questDescribe = questDescribe;
        this.questAnswer = questAnswer;
        this.questScore = questScore;
        this.questType = questType;
        this.questOrder = questOrder;
        this.answerId = answerId;
        this.answerDescribe = answerDescribe;
        this.answerOrder = answerOrder;
    }

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

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public int getQuestOrder() {
        return questOrder;
    }

    public void setQuestOrder(int questOrder) {
        this.questOrder = questOrder;
    }

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

    @Override
    public String toString() {
        return "QuestAnswerDto{" +
                "questId=" + questId +
                ", questDescribe='" + questDescribe + '\'' +
                ", questAnswer='" + questAnswer + '\'' +
                ", questScore=" + questScore +
                ", questType='" + questType + '\'' +
                ", questOrder=" + questOrder +
                ", answerId=" + answerId +
                ", answerDescribe='" + answerDescribe + '\'' +
                ", answerOrder=" + answerOrder +
                '}';
    }
}
