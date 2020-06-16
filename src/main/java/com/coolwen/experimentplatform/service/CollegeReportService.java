package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.CollegeReport;
import com.coolwen.experimentplatform.model.DTO.CollegeReportStuExpDto;

import java.util.List;

/**
 * @author 朱治汶
 */
public interface CollegeReportService {
    CollegeReport findStuidAndMid(int id, int mid);

    void addCollegeReport(CollegeReport collegeReport);

    CollegeReportStuExpDto findByStuidMid(int id, int mid);

    List<CollegeReport> findCollegeReportByMid(int mid);

    void deleteCollege(int mid);

    void deleteCollegeList(List<CollegeReport> list);

    void deleteByStuIdModelId(int m_id, int id);
}
