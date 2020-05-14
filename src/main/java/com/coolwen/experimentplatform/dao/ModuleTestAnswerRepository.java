package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * @author 淮南
 * @date 2020/5/12 12:05
 */
public interface ModuleTestAnswerRepository extends BaseRepository<ModuleTestAnswer,Integer> ,JpaSpecificationExecutor<ModuleTestAnswer> {


    public ModuleTestAnswer findByAnswerId(int answerId);

    ModuleTestAnswer findAllByQuestId(int questId);
    
}
