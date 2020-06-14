package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.CollegeReport;
import com.coolwen.experimentplatform.model.DTO.CollegeReportStuExpDto;

/**
 * @author 朱治汶
 */
public interface CollegeReportService {
    CollegeReport findStuidAndMid(int id, int mid);

    void addCollegeReport(CollegeReport collegeReport);

    CollegeReportStuExpDto findByStuidMid(int id, int mid);
}
