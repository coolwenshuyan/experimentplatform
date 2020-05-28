package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 20:12
 */
public interface ReportService {

    public void addReport(Report report);

    public int deleteReport(int reportId);

    public Report updateReport(int reportId);

    public List<Report> loadReport();

    Report findByReportId(int reportId);

    List<Report> findAllByReportId(int reportId);


    List<Report> findByMid(int mid);

    Page<Report> findByReportPage(Pageable pageable,int mId);

    List<Report> findReportByMId(int mid);

    void deleteReports(List<Report> report);

}
