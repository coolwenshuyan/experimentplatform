package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyDao extends JpaRepository<Reply,Integer> {

    @Query(value="select * from t_reply where id = ?",nativeQuery=true)
    public Reply findById (int id);

    @Query(value="select * from t_reply where qid = ?",nativeQuery=true)
    public List<Reply> findByreplycontent(int qid);
}
