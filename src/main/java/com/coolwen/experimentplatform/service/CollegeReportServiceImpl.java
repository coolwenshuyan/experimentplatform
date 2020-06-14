package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.CollegeReportRepository;
import com.coolwen.experimentplatform.model.CollegeReport;
import com.coolwen.experimentplatform.model.DTO.CollegeReportStuExpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 朱治汶
 * @date 2020/6/13 23:47
 **/
@Service
public class CollegeReportServiceImpl implements CollegeReportService {

    @Autowired
    CollegeReportRepository collegeReportRepository;

    @Override
    public CollegeReport findStuidAndMid(int stuid, int mid) {
        return collegeReportRepository.findStuidAndMid(stuid,mid) ;
    }

    @Override
    public void addCollegeReport(CollegeReport collegeReport) {
        collegeReportRepository.save(collegeReport);
    }

    @Override
    public CollegeReportStuExpDto findByStuidMid(int id, int mid) {
        return collegeReportRepository.findByStuidMid(id,mid);
    }

    @Override
    public List<CollegeReport> findCollegeReportByMid(int mid) {
        return collegeReportRepository.findCollegeReportsByMid(mid);
    }

    @Override
    public void deleteCollege(int mid) {
        List<CollegeReport> collegeReports = collegeReportRepository.findCollegeReportsByMid(mid);
        collegeReportRepository.deleteAll(collegeReports);
    }

    @Override
    public void deleteCollegeList(List<CollegeReport> list) {
        collegeReportRepository.deleteAll(list);
    }

}
