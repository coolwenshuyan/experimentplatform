package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.model.ReportAnswer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 淮南
 * @date 2020/5/13 21:56
 */
public interface ReportAnswerRepository extends BaseRepository<ReportAnswer,Integer>,JpaSpecificationExecutor<ReportAnswer> {

    ReportAnswer findById(int id);

    String findByReportId(int reportId);




}
