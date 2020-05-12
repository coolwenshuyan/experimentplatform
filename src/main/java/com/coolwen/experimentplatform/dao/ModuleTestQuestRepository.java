package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:05
 */
public interface ModuleTestQuestRepository extends BaseRepository<ModuleTestQuest,Integer> ,JpaSpecificationExecutor<ModuleTestQuest> {



    @Query("select qs from ModuleTestQuest qs where qs.questDescribe like %?1")
    public List<ModuleTestQuest> findAllByQuestDescribe(@Param("questDescribe") String questDescribe);

    @Query("select q from ModuleTestQuest q where q.questId=?1")
    public ModuleTestQuest findQuestByQuestId(int questId);

    @Query("select quests from ModuleTestQuest quests order by mid asc ,questOrder asc ")
    List<ModuleTestQuest> findAll(int mid,int questOrder, Sort sort);



}
