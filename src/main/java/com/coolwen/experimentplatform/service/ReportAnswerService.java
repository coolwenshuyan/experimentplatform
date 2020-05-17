package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Report;
import com.coolwen.experimentplatform.model.ReportAnswer;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 21:57
 */
public interface ReportAnswerService {

    public void addReportAnswer(ReportAnswer reportAnswer);

    public void deleteReportAnswer(int id);

    public ReportAnswer updateReportAnswer(int id);

    public List<ReportAnswer> loadReportAnswer();

    ReportAnswer findByReportAnswerId(int id);


}
