package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Reply;
import com.coolwen.experimentplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 *
 *  @author yellow
 */
public interface ReplyRepository extends BaseRepository<Reply, Integer>, JpaRepository<Reply, Integer> {

//  删除该问题
    @Modifying
    @Transactional
    @Query("delete from Reply where qid in ?1")
    public void deleteByQid(int id);

//    通过id查出回复
    @Query(value = "select * from t_reply where id = ?", nativeQuery = true)
    public Reply findById(int id);

//    查出所有回复
    @Query(value = "select * from t_reply where qid = ?", nativeQuery = true)
    public List<Reply> findByreplycontent(int qid);

//    通过qid查出问题id
    @Query(value = "select id from t_question where qid = ?", nativeQuery = true)
    List<Integer> findByQid(int qid);

}
