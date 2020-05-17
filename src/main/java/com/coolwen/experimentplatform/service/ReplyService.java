package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Reply;

import java.util.List;

public interface ReplyService {

    //添加
    void add (Reply reply);

    //删
    void delete(int id);

    void deleteByQid(int id);

    //通过id查
    public List<Reply> findByreplycontent(int qid);

    public Reply findById(int id);

    //查所有
    public List<Reply> getAll();

    public List<Integer> findByQid(int qid);

}
