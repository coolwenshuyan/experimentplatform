package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.model.ExpModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ExpModelRepository extends BaseRepository<ExpModel,Integer> {

    @Query("select e from ExpModel e where e.m_name=?1")
    List<ExpModel> findExpModelsByM_name(String m_name);

    @Query(value ="select * from t_exp_model",nativeQuery=true)
    Page<ExpModel> findAllexp(Pageable pageable1);

    @Query(value = "select t_exp_model.imageurl from t_exp_model where m_id= ?",nativeQuery=true)
    String findexpimg(int parseInt);

    //找到改学生考核model的相关信息

    @Query("select e.m_id from ExpModel e where e.m_id =?1")
    int findByM_id(int m_id);

    @Query("select exp from ExpModel exp,KaoheModel km where exp.m_id = km.m_id and km.m_id = ?1")
    ExpModel findExpModelsByKaoheMid(int mid);


}
