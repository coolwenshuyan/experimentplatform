package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:05
 */
public interface ModuleTestAnswerRepository extends BaseRepository<ModuleTestAnswer,Integer> ,JpaSpecificationExecutor<ModuleTestAnswer> {


    public ModuleTestAnswer findByAnswerId(int answerId);

    List<ModuleTestAnswer> findAllByQuestId(int questId);

    List<ModuleTestAnswer> findAllByAnswerId(int answerId);

    //    @Query("delete ")
    @Transactional
    int deleteByAnswerId(int answerId);

    @Query("select a.answerId from ModuleTestAnswer a where answerId = ?1")
    int findByAId(int answerId);

    String findByAnswerDescribe(String answerDescribe);

}
