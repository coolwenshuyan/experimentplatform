package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Effect;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EffectRepository extends PagingAndSortingRepository<Effect,Integer> {

    @Query(value="select * from t_effect where id = ?",nativeQuery=true)
    Effect findeffectById(int id);
}
