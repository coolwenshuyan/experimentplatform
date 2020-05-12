package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 淮南
 * @date 2020/5/12 12:05
 */
public interface ModuleTestAnswerRepository extends BaseRepository<ModuleTestAnswer,Integer> ,JpaSpecificationExecutor<ModuleTestAnswer> {

//    @Modifying
//    @Query("update ModuleTestAnswer a set a.answerDescribe = ?2 where a.answerId = ?1")
//    public ModuleTestAnswer updateAnswer(Optional<ModuleTestAnswer> answerId, String answerDescribe);




}
