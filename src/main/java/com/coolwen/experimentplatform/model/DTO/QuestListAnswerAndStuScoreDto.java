package com.coolwen.experimentplatform.model.DTO;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;

import java.util.List;

/**
 * @author CoolWen
 * @version 2020-05-17 22:20
 */
public class QuestListAnswerAndStuScoreDto {
    private int questId;

    private String questDescribe;

    private String questAnswer;

    private float questScore;

    private String questType;

    private int questOrder;

    private int mId;

    List<ModuleTestAnswer> moduleTestAnswerList;

    private String stuAnswer;

    private int oneQuestScore;




    public List<ModuleTestAnswer> getModuleTestAnswerList() {
        return moduleTestAnswerList;
    }

    public void setModuleTestAnswerList(List<ModuleTestAnswer> moduleTestAnswerList) {
        this.moduleTestAnswerList = moduleTestAnswerList;
    }

    public QuestListAnswerAndStuScoreDto(int questId, String questDescribe, String questAnswer, float questScore, String questType, int questOrder, int mId, List<ModuleTestAnswer> moduleTestAnswerList) {
        this.questId = questId;
        this.questDescribe = questDescribe;
        this.questAnswer = questAnswer;
        this.questScore = questScore;
        this.questType = questType;
        this.questOrder = questOrder;
        this.mId = mId;
        this.moduleTestAnswerList = moduleTestAnswerList;
    }

    public QuestListAnswerAndStuScoreDto() {
    }

    public QuestListAnswerAndStuScoreDto(int questId, String questDescribe, String questAnswer, float questScore, String questType, int questOrder, int mId) {
        this.questId = questId;
        this.questDescribe = questDescribe;
        this.questAnswer = questAnswer;
        this.questScore = questScore;
        this.questType = questType;
        this.questOrder = questOrder;
        this.mId = mId;
    }

    public QuestListAnswerAndStuScoreDto(int questId, String questDescribe, String questAnswer, float questScore, String questType, int questOrder, int mId, List<ModuleTestAnswer> moduleTestAnswerList, String stuAnswer, int oneQuestScore) {
        this.questId = questId;
        this.questDescribe = questDescribe;
        this.questAnswer = questAnswer;
        this.questScore = questScore;
        this.questType = questType;
        this.questOrder = questOrder;
        this.mId = mId;
        this.moduleTestAnswerList = moduleTestAnswerList;
        this.stuAnswer = stuAnswer;
        this.oneQuestScore = oneQuestScore;
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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }


    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public int getOneQuestScore() {
        return oneQuestScore;
    }

    public void setOneQuestScore(int oneQuestScore) {
        this.oneQuestScore = oneQuestScore;
    }

    @Override
    public String toString() {
        return "QuestListAnswerAndStuScoreDto{" +
                "questId=" + questId +
                ", questDescribe='" + questDescribe + '\'' +
                ", questAnswer='" + questAnswer + '\'' +
                ", questScore=" + questScore +
                ", questType='" + questType + '\'' +
                ", questOrder=" + questOrder +
                ", mId=" + mId +
                ", moduleTestAnswerList=" + moduleTestAnswerList +
                ", stuAnswer='" + stuAnswer + '\'' +
                ", oneQuestScore=" + oneQuestScore +
                '}';
    }
}
