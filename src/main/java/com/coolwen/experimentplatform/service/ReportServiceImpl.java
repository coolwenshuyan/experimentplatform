package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ReportRepository;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import com.coolwen.experimentplatform.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 20:15
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public void addReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public void deleteReport(int reportId) {
        reportRepository.deleteById(reportId);
    }

    @Override
    public Report updateReport(int reportId) {
        Report report = reportRepository.findAllByReportId(reportId);
        return report;
    }

    @Override
    public List<Report> loadReport() {
        return reportRepository.findAll();
    }

    @Override
    public Report findByReportId(int reportId) {
        return null;
    }

}