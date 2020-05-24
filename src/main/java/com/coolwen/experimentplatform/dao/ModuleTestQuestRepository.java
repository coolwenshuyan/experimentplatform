package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.DTO.QuestAnswerDto;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:05
 */
public interface ModuleTestQuestRepository extends BaseRepository<ModuleTestQuest, Integer>, JpaSpecificationExecutor<ModuleTestQuest> {


    //    @Query("select qs from ModuleTestQuest qs where qs.questDescribe like CONCAT('%',:questDescribe,'%') or qs.mId =?2")
//    public List<ModuleTestQuest> findAllByQuestDescribeContainingAndMId(@Param("questDescribe") String questDescribe,@Param("mId") int mId);
    @Query("select qs from ModuleTestQuest qs where qs.questDescribe like %?1")
    public List<ModuleTestQuest> findAllByQuestDescribe(@Param("questDescribe") String questDescribe);

    @Query("select q from ModuleTestQuest q where q.questId=?1")
    public ModuleTestQuest findQuestByQuestId(int questId);

    @Query("select quests from ModuleTestQuest quests order by mid asc ,questOrder asc ")
    List<ModuleTestQuest> findAll(int mid, int questOrder, Sort sort);

    @Query("select q1.questId from ModuleTestQuest q1 where q1.questDescribe = ?1")
    String findByQuestDescribes(@Param("questDescribe") String questDescribe);

    @Query(value = "select qs from ModuleTestQuest qs where if(?1 !='',qs.questDescribe like concat('%',?1,'%'),1=1) and if(?2 !='',qs.mId=?2,1=1)", nativeQuery = true)
    public List<ModuleTestQuest> findAllByQuestDescribeAndMId(String questDescribe, int mId);

    @Query("select q from ModuleTestQuest q where q.mId=?1")
    List<ModuleTestQuest> findAllByMid(int mId);

    List<ModuleTestQuest> findAllByQuestId(int questId);

    @Query("SELECT COUNT(q) FROM ModuleTestQuest q")
    int countAllByQuestId();

    ModuleTestQuest findByQuestDescribe(@Param("questDescribe") String questDescribe);

    @Query(value = "select b from ModuleTestQuest b where b.mId=?1")
    Page<ModuleTestQuest> findTermQuest(@Param("mId") int mId, Pageable pageable);

    @Query("select new com.coolwen.experimentplatform.model.DTO.QuestAnswerDto" +
            "(t1.questId,t1.questDescribe,t1.questAnswer,t1.questScore,t1.questType,t1.questOrder," +
            "t2.answerId,t2.answerDescribe,t2.answerOrder) " +
            "from ModuleTestQuest t1 left join ModuleTestAnswer t2 on t1.questId=t2.questId " +
            "where t1.mId=?1and t1.questType=?1")
    List<QuestAnswerDto> findQuestAnswerByMid(int mId, String questType);

    @Query("select mtq from ModuleTestQuest mtq where mtq.mId = ?1")
    List<ModuleTestQuest> findModuleTestQuestByMId(int mid);


    @Query("select q from ExpModel e, ModuleTestQuest q where e.m_id =q.mId and e.m_id = ?1")
    Page<ModuleTestQuest> findByExpPage(@Param("mid") int mid, Pageable pageable);


}
