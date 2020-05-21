package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestAnswerStu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleTestAnswerStuRepository extends BaseRepository<ModuleTestAnswerStu,Integer> {

    @Query("select a from ModuleTestAnswerStu as a where a.stu_id=?1 and a.quest_id = ?2")
    List<ModuleTestAnswerStu> findModuleTestAnswerStusByStu_idAndQuest_id(int Stuid,int QuestId);

    @Query("select mtas from ModuleTestAnswerStu mtas where mtas.quest_id = ?1")
    List<ModuleTestAnswerStu> findModuleTestAnswerStuByQuest_id(int questid);

}
