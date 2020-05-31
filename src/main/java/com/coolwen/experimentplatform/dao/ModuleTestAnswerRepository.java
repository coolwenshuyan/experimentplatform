package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:05
 */
public interface ModuleTestAnswerRepository extends BaseRepository<ModuleTestAnswer,Integer> ,JpaSpecificationExecutor<ModuleTestAnswer> {


//    通过模块测试题选项id找到该选项的信息
    public ModuleTestAnswer findByAnswerId(int answerId);

//    通过问题id找到所有选项信息
    List<ModuleTestAnswer> findAllByQuestId(int questId);

//    List<ModuleTestAnswer> findAllByAnswerId(int answerId);

//    删除选项
    @Transactional
    int deleteByAnswerId(int answerId);

//    查找选项id
    @Query("select a.answerId from ModuleTestAnswer a where answerId = ?1")
    int findByAId(int answerId);

//    String findByAnswerDescribe(String answerDescribe);


}
