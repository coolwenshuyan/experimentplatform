package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Effect;
import com.coolwen.experimentplatform.model.NewsInfo;
import com.google.inject.internal.cglib.proxy.$FixedValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NewsInfoRepository  extends PagingAndSortingRepository<NewsInfo,Integer> {

    @Query(value="select * from t_newsinfo where id = ?",nativeQuery=true)
    NewsInfo findByInfo(int id);

    @Query(value ="select * from t_newsinfo order by t_newsinfo.dic_datetime desc ",nativeQuery=true)
    public Page<NewsInfo> findAllorderby(Pageable pageable);

    @Query(value ="select count(*) from t_totalscore_current",nativeQuery=true)
    int findAllmodelpeople();

    @Query(value = "select count(*) from t_totalscore_pass where total_score>60",nativeQuery=true)
    int findAllPass();
}
