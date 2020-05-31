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

//    添加模块测试的实验报告
    void addReport(Report report);

//    删除实验报告
    int deleteReport(int reportId);

//    更新实验报告的信息
    Report updateReport(int reportId);

//    public List<Report> loadReport();

//    通过实验报告的id找到该实验报告
    Report findByReportId(int reportId);

//    List<Report> findAllByReportId(int reportId);

//    通过模块id找到所以实验报告
    List<Report> findByMid(int mid);

//    通过模块id分页
    Page<Report> findByReportPage(Pageable pageable,int mId);

//    通过模块id找到所以实验报告
    List<Report> findReportByMId(int mid);

    void deleteReports(List<Report> report);

}
