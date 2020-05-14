package com.coolwen.experimentplatform.dao;


import com.coolwen.experimentplatform.model.SetInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SetInfoRepository extends PagingAndSortingRepository<SetInfo,Integer> {


    public SetInfo findById(int id);
}
