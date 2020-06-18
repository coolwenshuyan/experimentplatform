package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.model.ReportAnswer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 21:56
 */
public interface ReportAnswerRepository extends BaseRepository<ReportAnswer,Integer>,JpaSpecificationExecutor<ReportAnswer> {

    ReportAnswer findById(int id);

    String findNameByReportId(int reportId);

    ReportAnswer findByReportId(int reportId);

    ReportAnswer findByReportIdAndAndStuId(int reportId,int stuid);

    List<ReportAnswer> findAllByStuId(int stuid);

    List<ReportAnswer> findAllByReportIdAndAndStuId(int reportId,int stuid);

    @Query("select ra from ReportAnswer  ra where ra.reportId = ?1")
    List<ReportAnswer> findReportAnswerByReportId(int reportId);

    @Query("select ra from ReportAnswer ra where ra.stuId = ?1")
    List<ReportAnswer> findReportAnswerByStuId(int id);

    @Query(value="select count(*) from t_mreport,t_mreport_answer where t_mreport.m_id=? and t_mreport_answer.stu_id=? and t_mreport.report_id=t_mreport_answer.report_id",nativeQuery=true)
    int findByStuIdModelId(int mid, int stuId);

//    @Query("delete from ReportAnswer ra where ra.reportId in(select r.reportId from Report r where r.mId = ?1)")
//    void deleteReportAnswerByMid(int mid);

    @Modifying
    @Transactional
    @Query(value="DELETE t_mreport_answer FROM t_mreport,t_mreport_answer WHERE t_mreport.report_id=t_mreport_answer.report_id and t_mreport.m_id = ?",nativeQuery=true)
    void deleteReportAnswerByMid(int mid);

    @Modifying
    @Transactional
    @Query(value="DELETE t_mreport_answer FROM t_mreport,t_mreport_answer WHERE t_mreport.report_id=t_mreport_answer.report_id and t_mreport.m_id = ? and t_mreport_answer.stu_id=?",nativeQuery=true)
    void deleteByStuIdModelId(int m_id, int id);
}
