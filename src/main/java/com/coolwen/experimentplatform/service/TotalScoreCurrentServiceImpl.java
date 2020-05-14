package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.TotalScoreCurrentRepository;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Artell
 * @version 2020/5/12 18:12
 */
@Service
public class TotalScoreCurrentServiceImpl implements TotalScoreCurrentService {

    @Autowired
    private TotalScoreCurrentRepository totalScoreCurrentRepository;

    @Override
    public void add(TotalScoreCurrent res) {
        totalScoreCurrentRepository.save(res);
    }

    @Override
    public void update(TotalScoreCurrent res) {
        totalScoreCurrentRepository.save(res);
    }

    @Override
    public void delete(int id) {
        totalScoreCurrentRepository.deleteById(id);
    }

    @Override
    public TotalScoreCurrent load(int id) {
        return totalScoreCurrentRepository.findById(id).get();
    }

    @Override
    public List<TotalScoreCurrent> listTotalScoreCurrent() {
        return totalScoreCurrentRepository.findAll();
    }
}
