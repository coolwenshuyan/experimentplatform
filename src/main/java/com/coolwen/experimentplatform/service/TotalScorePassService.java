package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.TotalScorePass;

public interface TotalScorePassService {
    void delteTotalScorePassByStuId(int id);

    void save(TotalScorePass totalScorePass);
}
