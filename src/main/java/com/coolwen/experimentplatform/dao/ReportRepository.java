package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.Report;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 淮南
 * @date 2020/5/13 20:12
 */
public interface ReportRepository extends BaseRepository<Report,Integer>,JpaSpecificationExecutor<Report> {

//    @Query("select r from Report r ")
    Report findAllByReportId(int reportId);

}
