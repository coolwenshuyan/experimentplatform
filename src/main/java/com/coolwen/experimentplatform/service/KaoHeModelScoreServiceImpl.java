package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.KaoHeModelScoreRepository;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 17:31
 * @Description: TODO
 */
public class KaoHeModelScoreServiceImpl implements KaoHeModelScoreService {

    @Autowired
    private KaoHeModelScoreRepository kaoHeModelScoreRepository;

    @Override
    public void add(KaoHeModelScore res) {
        kaoHeModelScoreRepository.save(res);
    }

    @Override
    public void update(KaoHeModelScore res) {
        kaoHeModelScoreRepository.save(res);
    }

    @Override
    public void delete(int id) {
        kaoHeModelScoreRepository.deleteById(id);
    }

    @Override
    public KaoHeModelScore load(int id) {
        return kaoHeModelScoreRepository.findById(id).get();
    }

    @Override
    public List<KaoHeModelScore> listKaoHeModleScore() {
        return kaoHeModelScoreRepository.findAll();
    }
}
