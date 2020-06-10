package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.ModuleTestAnswerStu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ModuleTestAnswerStuRepository extends BaseRepository<ModuleTestAnswerStu,Integer> {

    @Query("select a from ModuleTestAnswerStu as a where a.stu_id=?1 and a.quest_id = ?2")
    List<ModuleTestAnswerStu> findModuleTestAnswerStusByStu_idAndQuest_id(int Stuid,int QuestId);


    @Query("select a from ModuleTestAnswerStu as a where a.stu_id=?1 and a.quest_id = ?2")
    ModuleTestAnswerStu findModuleTestAnswerStuByStu_idAndQuest_id(int stuid,int questid);

    @Query("select mtas from ModuleTestAnswerStu mtas where mtas.quest_id = ?1")
    List<ModuleTestAnswerStu> findModuleTestAnswerStuByQuest_id(int questid);

    @Query("select mtas from ModuleTestAnswerStu mtas where mtas.stu_id = ?1")
    List<ModuleTestAnswerStu> findModuleTestAnswerStuByStu_id(int id);

    @Modifying
    @Transactional(readOnly = false)
    @Query("delete from ModuleTestAnswerStu mtas where mtas.quest_id = ?1")
    void deleteAllByQuest_id(int quset_id);

}
