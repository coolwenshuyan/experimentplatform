package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.TotalScorePass;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TotalScorePassService {
    void delteTotalScorePassByStuId(int id);

    void save(TotalScorePass totalScorePass);

    Page<TotalScorePass> findAll(int pageNum);

    Page<TotalScorePass> findAllByClassId(int classId);


    List<TotalScorePass> findByStuId(int stuId);
}
