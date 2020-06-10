package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Reply;

import java.util.List;
/**
 *
 *  @author yellow
 */
public interface ReplyService {

    //添加回复
    void add(Reply reply);

    //删除
    void delete(int id);

//  通过qid删除
    void deleteByQid(int id);

    //通过qid查
    public List<Reply> findByreplycontent(int qid);

//    通过id查回复
    public Reply findById(int id);

    //查所有
    public List<Reply> getAll();

    //通过qid查问题的id
    public List<Integer> findByQid(int qid);

}
