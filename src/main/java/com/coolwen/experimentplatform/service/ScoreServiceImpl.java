package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ScoreRepository;
import com.coolwen.experimentplatform.model.DTO.PScoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<PScoreDto> listScorerDTOBystudentId(int stuId,int mid) {
        return scoreRepository.listScorerDTOBystudentId(stuId,mid);
    }
}
