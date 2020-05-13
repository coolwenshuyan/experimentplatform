package com.coolwen.experimentplatform.service;


import com.coolwen.experimentplatform.model.Effect;
import org.springframework.stereotype.Service;


public interface EffectService {

    void add(Effect effect);

    Effect findById(int id);

    void delete(int id);
}
