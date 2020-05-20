package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ReplyRepository;
import com.coolwen.experimentplatform.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@org.springframework.stereotype.Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;


    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public void delete(int id) {
        replyRepository.deleteById(id);
    }

    @Override
    public void deleteByQid(int id) {
////        List<Integer> ids = replyRepository.findByQid(id);
////        replyRepository.deleteByQid(ids);
//        Reply reply = entityManager.find(Reply.class, id);
//        replyRepository.updateByHql("delete from Reply where id=?",id);
//        //强行抛出异常,验证声明式事务是否起作用
//        if(id>1) {
//            throw new RuntimeException();
//        }
//        entityManager.remove(reply);

        replyRepository.deleteByQid(id);

    }

    @Override
    public List<Reply> findByreplycontent(int qid) {
        return replyRepository.findByreplycontent(qid);
    }

    @Override
    public Reply findById(int id) {
        Reply reply = new Reply();
        reply = replyRepository.findById(id);
        return reply;
    }

    @Override
    public List<Reply> getAll() {
        return null;
    }

    @Override
    public List<Integer> findByQid(int qid) {
        return replyRepository.findByQid(qid);
    }
}
