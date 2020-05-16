package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.KaoHeModelScoreRepository;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
@Service
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
    public void deleteAllByKaohemId(Integer kaohemid) {
        kaoHeModelScoreRepository.deleteByTKaohemodleId(kaohemid);
    }


    @Override
    public void deleteByStuId(int sid) {
        kaoHeModelScoreRepository.deleteById(sid);
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
