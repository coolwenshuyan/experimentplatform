package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.KaoHeModleScoreRepository;
import com.coolwen.experimentplatform.model.KaoHeModleScore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 17:31
 * @Description: TODO
 */
public class KaoHeModelScoreServiceImpl implements KaoHeModelScoreService {

    @Autowired
    private KaoHeModleScoreRepository kaoHeModleScoreRepository;

    @Override
    public void add(KaoHeModleScore res) {
        kaoHeModleScoreRepository.save(res);
    }

    @Override
    public void update(KaoHeModleScore res) {
        kaoHeModleScoreRepository.save(res);
    }

    @Override
    public void delete(int id) {
        kaoHeModleScoreRepository.deleteById(id);
    }

    @Override
    public KaoHeModleScore load(int id) {
        return kaoHeModleScoreRepository.findById(id).get();
    }

    @Override
    public List<KaoHeModleScore> listKaoHeModleScore() {
        return kaoHeModleScoreRepository.findAll();
    }
}
