package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.DTO.QuestListAnswerAndStuScoreDto;
import com.coolwen.experimentplatform.model.DTO.QuestListAnswerDto;

import java.util.List;

/**
 * @author CoolWen
 * @version 2020-05-17 22:24
 */
public interface QuestListAnswerRepositoryCustom {

    /**
     * 查询问题dto
     *
     * @return
     */
    public List<QuestListAnswerDto> listQuestAnswerDto(String type, int mId);

    public List<QuestListAnswerAndStuScoreDto> listQuestListAnswerAndStuScoreDto(String type, int mId, int stuId);


}
