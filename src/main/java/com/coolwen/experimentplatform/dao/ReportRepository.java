package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 20:12
 */
public interface ReportRepository extends BaseRepository<Report, Integer>, JpaSpecificationExecutor<Report> {


    //    @Query("select r from Report r ")
    Report findByReportId(int reportId);

    @Transactional
    int deleteByReportId(int reportId);

    List<Report> findAllByReportId(int reportId);

    //findAllByMid找不到啊
    @Query("select r from Report r where r.mId = ?1")
    List<Report> findAllByMId(int mid);

    @Query("select r from Report r where r.mId = ?1")
    List<Report> findReportByMId(int mid);

    @Query("select q from ExpModel e, Report q where e.m_id =q.mId and e.m_id = ?1")
    Page<Report> findByReportPage(@Param("mid") int mid, Pageable pageable);
}
