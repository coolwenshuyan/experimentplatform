package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.DTO.ModuleGradesDto;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import com.google.inject.internal.cglib.proxy.$FixedValue;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
public interface TotalScoreCurrentRepository extends BaseRepository<TotalScoreCurrent, Integer>, JpaSpecificationExecutor<TotalScoreCurrent> {

    @Modifying
    @Transactional
    @Query(value="select * from t_totalscore_current where stu_id = ?",nativeQuery=true)
    List<TotalScoreCurrent> findeAllBystuid(int uid);

//    @Query("select new com.coolwen.experimentplatform.model.DTO.ModuleGradesDto" +
//            "(t3.m_id,t3.m_name,t1.m_test_score,t1.m_report_score,t1.m_score) " +
//            "from t_kaohemodel_score t1,t_kaohemodel t2,t_exp_model t3 where " +
//            "t3.m_id=t2.m_id and t1.t_kaohemodle_id=t2.id and t1.stu_id = ?1")

//    @Query(value = "select t_exp_model.m_id,t_exp_model.m_name,t_kaohemodel_score.m_test_score,t_kaohemodel_score.m_report_score,t_kaohemodel_score.m_score from t_kaohemodel_score,t_kaohemodel,t_exp_model where t_exp_model.m_id=t_kaohemodel.m_id and t_kaohemodel_score.t_kaohemodle_id=t_kaohemodel.id and t_kaohemodel_score.stu_id = ?",nativeQuery=true)
//    List<Object> ModuleGrade(int uid);
//    ("select new com.coolwen.experimentplatform.model.DTO.StudentVo(s.id,s.stuUname,s.stuPassword,s.stuName,s.stuXuehao,s.stuMobile,s.stuCheckstate,s.stuIsinschool,c.className) from Student s left join ClassModel c on s.classId = c.classId where s.stuCheckstate = true and s.id = ?1")
    @Query("select new com.coolwen.experimentplatform.model.DTO.ModuleGradesDto(t3.m_id,t3.m_name,t1.mTestScore,t1.mReportScore,t1.mScore) from KaoheModel t2 left join ExpModel t3 on t2.m_id=t3.m_id left join KaoHeModelScore t1 on t2.id= t1.tKaohemodleId where t1.stuId = ?1")
    List<ModuleGradesDto> ModuleGrade(int uid);

    @Query("select t from TotalScoreCurrent t where t.stuId = ?1")
    TotalScoreCurrent findTotalScoreCurrentByStuID(int stuId);
}
