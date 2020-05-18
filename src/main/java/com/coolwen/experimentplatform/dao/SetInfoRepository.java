package com.coolwen.experimentplatform.dao;


import com.coolwen.experimentplatform.model.SetInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SetInfoRepository extends PagingAndSortingRepository<SetInfo,Integer> {


    public SetInfo findById(int id);

    @Query(value = "select t_exp_model.imageurl from t_exp_model where m_id= ?",nativeQuery=true)
    String findexpimg(int id);
}
