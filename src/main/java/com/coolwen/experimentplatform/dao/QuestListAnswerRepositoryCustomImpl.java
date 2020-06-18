package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.DTO.QuestListAnswerAndStuScoreDto;
import com.coolwen.experimentplatform.model.DTO.QuestListAnswerDto;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoolWen
 * @version 2020-05-17 22:30
 */
@Transactional(readOnly = true)
@Repository
public class QuestListAnswerRepositoryCustomImpl implements QuestListAnswerRepositoryCustom {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestListAnswerDto> listQuestAnswerDto(String type, int mId) {

        String hql = "select q.questId,q.questDescribe,q.questAnswer,q.questScore,q.questType,q.questOrder,a.answerId,a.answerDescribe, a.answerOrder,a.questId,q.mId " +
                "from ModuleTestQuest q right join ModuleTestAnswer a " +
                "on q.questId=a.questId where q.questType=:type and q.mId=:mid";
//        String hql = "from ModuleTestQuest";
        List<Object[]> list = entityManager.createQuery(hql)
                .setParameter("type", type)
                .setParameter("mid", mId)
                .getResultList();
        List<QuestListAnswerDto> questListAnswerDtos = new ArrayList<QuestListAnswerDto>();
        List<ModuleTestAnswer> answesrs = null;
//        System.out.println(list.size());
        QuestListAnswerDto cd = null;
        for (Object[] objs : list) {
            ModuleTestAnswer answesr = new ModuleTestAnswer();
            answesr.setAnswerId((Integer) objs[6]);
            answesr.setAnswerDescribe((String) objs[7]);
            answesr.setAnswerOrder((Integer) objs[8]);
            answesr.setQuestId((Integer) objs[9]);
            if (!checkExist(questListAnswerDtos, (Integer) objs[0])) {
                answesrs = new ArrayList<ModuleTestAnswer>();
                answesrs.add(answesr);
                cd = new QuestListAnswerDto();
                cd.setQuestId((Integer) objs[0]);
                cd.setQuestDescribe((String) objs[1]);
                cd.setQuestAnswer((String) objs[2]);
                cd.setQuestScore((float) objs[3]);
                cd.setQuestType((String) objs[4]);
                cd.setQuestOrder((Integer) objs[5]);
                cd.setModuleTestAnswerList(answesrs);
                if (!checkExist(questListAnswerDtos, cd.getQuestId())) {
                    questListAnswerDtos.add(cd);
                }
            } else {
                cd.getModuleTestAnswerList().add(answesr);
            }
//            System.out.println(objs.toString());
        }
        return questListAnswerDtos;
    }

    @Override
    public List<QuestListAnswerAndStuScoreDto> listQuestListAnswerAndStuScoreDto(String type, int mId, int stuId) {

        String hql = "select q.questId,q.questDescribe,q.questAnswer,q.questScore,q.questType,q.questOrder,a.answerId,a.answerDescribe, a.answerOrder,a.questId,q.mId, " +
                "mtas.stu_quest_answer, mtas.score " +
                "from ModuleTestQuest q right join ModuleTestAnswer a " +
                "on q.questId=a.questId " +
                "right join ModuleTestAnswerStu mtas on a.questId = mtas.quest_id " +
                "where q.questType=:type and q.mId=:mid and mtas.stu_id =: stuId";
//        String hql = "from ModuleTestQuest";
        List<Object[]> list = entityManager.createQuery(hql)
                .setParameter("type", type)
                .setParameter("mid", mId)
                .setParameter("stuId", stuId)
                .getResultList();
        List<QuestListAnswerAndStuScoreDto> questListAnswerAndStuScoreDtos = new ArrayList<QuestListAnswerAndStuScoreDto>();
        List<ModuleTestAnswer> answesrs = null;
//        System.out.println(list.size());
        QuestListAnswerAndStuScoreDto cd = null;
        for (Object[] objs : list) {
            ModuleTestAnswer answesr = new ModuleTestAnswer();
            answesr.setAnswerId((Integer) objs[6]);
            answesr.setAnswerDescribe((String) objs[7]);
            answesr.setAnswerOrder((Integer) objs[8]);
            answesr.setQuestId((Integer) objs[9]);
            if (!checkExist2(questListAnswerAndStuScoreDtos, (Integer) objs[0])) {
                answesrs = new ArrayList<ModuleTestAnswer>();
                answesrs.add(answesr);
                cd = new QuestListAnswerAndStuScoreDto();
                cd.setQuestId((Integer) objs[0]);
                cd.setQuestDescribe((String) objs[1]);
                cd.setQuestAnswer((String) objs[2]);
                cd.setQuestScore((float) objs[3]);
                cd.setQuestType((String) objs[4]);
                cd.setQuestOrder((Integer) objs[5]);
                cd.setModuleTestAnswerList(answesrs);
                cd.setStuAnswer((String) objs[11]);
                cd.setOneQuestScore((Integer) objs[12]);
                if (!checkExist2(questListAnswerAndStuScoreDtos, cd.getQuestId())) {
                    questListAnswerAndStuScoreDtos.add(cd);
                }
            } else {
                cd.getModuleTestAnswerList().add(answesr);
            }
//            System.out.println(objs.toString());
        }

        return questListAnswerAndStuScoreDtos;
    }

    private boolean checkExist(List<QuestListAnswerDto> questListAnswerDtos, Integer id) {
        for (QuestListAnswerDto cd : questListAnswerDtos) {
            if (cd.getQuestId() == id) {
                return true;
            }
        }
        return false;
    }

    private boolean checkExist2(List<QuestListAnswerAndStuScoreDto> questListAnswerDtos, Integer id) {
        for (QuestListAnswerAndStuScoreDto cd : questListAnswerDtos) {
            if (cd.getQuestId() == id) {
                return true;
            }
        }
        return false;
    }
}
