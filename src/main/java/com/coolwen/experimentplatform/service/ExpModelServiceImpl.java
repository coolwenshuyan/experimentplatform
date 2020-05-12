package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ExpModelRepository;
import com.coolwen.experimentplatform.model.ExpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExpModelServiceImpl implements ExpModelService {
    @Autowired
    ExpModelRepository expModelRepository;
    @Override
    public void save(ExpModel expModel) {
        expModelRepository.save(expModel);
    }

    @Override
    public ExpModel findExpModelByID(int id) {
        return expModelRepository.findById(id).get();
    }

    @Override
    public void deleteExpModelById(int id) {
        ExpModel expModel = findExpModelByID(id);
        expModelRepository.delete(expModel);
    }

    @Override
    public Page<ExpModel> findModelList(int pageNum) {
        Pageable pageable  = PageRequest.of(pageNum,10);
        return expModelRepository.findAll(pageable);
    }
}
