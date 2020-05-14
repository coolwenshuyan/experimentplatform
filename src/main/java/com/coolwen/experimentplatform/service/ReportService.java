package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.Report;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 20:12
 */
public interface ReportService {

    public void addReport(Report report);

    public void deleteReport(int reportId);

    public Report updateReport(int reportId);

    public List<Report> loadReport();

    Report findByReportId(int reportId);
}
