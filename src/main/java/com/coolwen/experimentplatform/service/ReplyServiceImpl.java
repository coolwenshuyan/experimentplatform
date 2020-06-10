package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ReplyRepository;
import com.coolwen.experimentplatform.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
/**
 *
 *  @author yellow
 */
@org.springframework.stereotype.Service
public class ReplyServiceImpl implements ReplyService {

//    注入
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

//    添加回复
    @Override
    public void add(Reply reply) {
        replyRepository.save(reply);
    }

//    删除回复
    @Override
    public void delete(int id) {
        replyRepository.deleteById(id);
    }

//    通过qid删回复
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

    //通过qid查
    @Override
    public List<Reply> findByreplycontent(int qid) {
        return replyRepository.findByreplycontent(qid);
    }

    //通过id查回复
    @Override
    public Reply findById(int id) {
        Reply reply = new Reply();
        reply = replyRepository.findById(id);
        return reply;
    }

    //查所有
    @Override
    public List<Reply> getAll() {
        return null;
    }

    //通过qid查问题的id
    @Override
    public List<Integer> findByQid(int qid) {
        return replyRepository.findByQid(qid);
    }
}
