package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.DTO.PScoreDto;

import java.util.List;

public interface ScoreService {
    List<PScoreDto> listScorerDTOBystudentId(int stuId,int mid);
}
