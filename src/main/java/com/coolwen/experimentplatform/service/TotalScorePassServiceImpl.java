package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.TotalScorePassRepository;
import com.coolwen.experimentplatform.model.TotalScorePass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalScorePassServiceImpl implements TotalScorePassService {
    @Autowired
    TotalScorePassRepository totalScorePassRepository;
    @Override
    public void delteTotalScorePassByStuId(int id) {
        TotalScorePass totalScorePass = totalScorePassRepository.findTotalScorePassByStuId(id);
        if(totalScorePass != null){
            totalScorePassRepository.delete(totalScorePass);

        }
    }

    @Override
    public void save(TotalScorePass totalScorePass) {
        totalScorePassRepository.save(totalScorePass);
    }
}
