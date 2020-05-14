package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ReportAnswerRepository;
import com.coolwen.experimentplatform.model.ReportAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/13 21:59
 */
@Service
public class ReportAnswerServiceImpl implements ReportAnswerService {

    @Autowired
    private ReportAnswerRepository reportAnswerRepository;

    @Override
    public void addReportAnswer(ReportAnswer reportAnswer) {
        reportAnswerRepository.save(reportAnswer);
    }

    @Override
    public void deleteReportAnswer(int id) {
        reportAnswerRepository.deleteById(id);
    }

    @Override
    public ReportAnswer updateReportAnswer(int id) {
        ReportAnswer reportAnswer = reportAnswerRepository.findAllById(id);
        return reportAnswer;
    }

    @Override
    public List<ReportAnswer> loadReportAnswer() {
        return reportAnswerRepository.findAll();
    }

    @Override
    public ReportAnswer findByReportAnswerId(int id) {
        return null;
    }
}
