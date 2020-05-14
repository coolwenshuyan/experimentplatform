package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ExpModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ExpModelRepository extends BaseRepository<ExpModel,Integer> {

    @Query("select e from ExpModel e where e.m_name=?1")
    List<ExpModel> findExpModelsByM_name(String m_name);

}
