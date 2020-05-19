package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Reply;
import com.coolwen.experimentplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReplyRepository extends BaseRepository<Reply, Integer>, JpaRepository<Reply, Integer> {

    @Modifying
    @Transactional
    @Query("delete from Reply where qid in ?1")
    public void deleteByQid(int id);

    @Query(value = "select * from t_reply where id = ?", nativeQuery = true)
    public Reply findById(int id);

    @Query(value = "select * from t_reply where qid = ?", nativeQuery = true)
    public List<Reply> findByreplycontent(int qid);

    @Query(value = "select id from t_question where qid = ?", nativeQuery = true)
    List<Integer> findByQid(int qid);

}
