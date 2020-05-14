package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.EffectRepository;
import com.coolwen.experimentplatform.model.Effect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EffectServiceImpl implements EffectService {

    @Autowired
    EffectRepository effectRepository;

    @Override
    public void add(Effect effect) {
        effectRepository.save(effect);
    }

    @Override
    public Effect findById(int id) {
        Effect effect = new Effect();
        effect = effectRepository.findeffectById(id);
        return effect;
    }

    @Override
    public void delete(int id) {
        effectRepository.deleteById(id);
    }
}
