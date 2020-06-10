package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.TotalScorePass;
import org.springframework.data.domain.Page;

public interface TotalScorePassService {
    void delteTotalScorePassByStuId(int id);

    void save(TotalScorePass totalScorePass);

    Page<TotalScorePass> findAll(int pageNum);

    Page<TotalScorePass> findAllByClassId(int classId);


}
