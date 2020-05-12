package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.EffectDao;
import com.coolwen.experimentplatform.model.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EffectServiceImpl implements EffectService {

    @Autowired
    EffectDao effectDao;

    @Override
    public void add(Effect effect) {
        effectDao.save(effect);
    }

    @Override
    public Effect findById(int id) {
        Effect effect = new Effect();
        effect = effectDao.findeffectById(id);
        return effect;
    }

    @Override
    public void delete(int id) {
        effectDao.deleteById(id);
    }
}
