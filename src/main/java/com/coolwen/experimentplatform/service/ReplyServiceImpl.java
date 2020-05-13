package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ReplyDao;
import com.coolwen.experimentplatform.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyDao replyDao;

    @Override
    public void add(Reply reply) {
        replyDao.save(reply);
    }

    @Override
    public void delete(int id) {
        replyDao.deleteById(id);
    }

    @Override
    public List<Reply> findByreplycontent(int qid) {
        return replyDao.findByreplycontent(qid);
    }

    @Override
    public Reply findById(int id) {
        Reply reply = new Reply();
        reply = replyDao.findById(id);
        return reply;
    }

    @Override
    public List<Reply> getAll() {
        return null;
    }
}
