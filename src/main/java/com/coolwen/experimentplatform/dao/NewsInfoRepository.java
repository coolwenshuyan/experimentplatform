package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.NewsInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsInfoRepository  extends PagingAndSortingRepository<NewsInfo,Integer> {

    @Query(value="select * from t_newsinfo where id = ?",nativeQuery=true)
    NewsInfo findByInfo(int id);
}
