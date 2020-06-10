package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.TotalScorePassRepository;
import com.coolwen.experimentplatform.model.DTO.StudentVo;
import com.coolwen.experimentplatform.model.TotalScorePass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TotalScorePassServiceImpl implements TotalScorePassService {
    @Autowired
    TotalScorePassRepository totalScorePassRepository;
    @Override
    public void delteTotalScorePassByStuId(int id) {
        TotalScorePass totalScorePass = totalScorePassRepository.findTotalScorePassByStuId(id);
        if(totalScorePass != null){
            totalScorePassRepository.delete(totalScorePass);

        }
    }

    @Override
    public void save(TotalScorePass totalScorePass) {
        totalScorePassRepository.save(totalScorePass);
    }

    @Override
    public Page<TotalScorePass> findAll(int pageNum) {
        System.out.println("成功进入");
        Pageable pageable  = PageRequest.of(pageNum,10);

        return totalScorePassRepository.findAll(pageable);
    }


    @Override
    public Page<TotalScorePass> findAllByClassId(int classId) {
        return null;
    }
}
