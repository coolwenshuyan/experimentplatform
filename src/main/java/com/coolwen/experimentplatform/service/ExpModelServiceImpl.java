package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ExpModelRepository;
import com.coolwen.experimentplatform.dao.ModuleTestAnswerStuRepository;
import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.model.ExpModel;
import com.coolwen.experimentplatform.model.ModuleTestAnswerStu;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpModelServiceImpl implements ExpModelService {
    @Autowired
    ExpModelRepository expModelRepository;
    @Autowired
    ModuleTestQuestRepository moduleTestQuestRepository;
    @Autowired
    ModuleTestAnswerStuRepository moduleTestAnswerStuRepository;
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

    @Override
    public List<ExpModel> findExpModelsBym_name(String m_name) {
        return expModelRepository.findExpModelsByM_name(m_name);
    }

    @Override
    public List<ExpModel> findAll() {
        return expModelRepository.findAll();
    }

    @Override
    public Page<ExpModel> finExpAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,6);
        return expModelRepository.findAll(pageable);
    }

    @Override
    public List<ModuleTestQuest> findModuleTestQuestByMId(int mid) {
        return moduleTestQuestRepository.findModuleTestQuestByMId(mid);
    }

    @Override
    public void deleteModuleTestAnswerStuByQuestId(int questid) {
        List<ModuleTestAnswerStu> list = moduleTestAnswerStuRepository.findModuleTestAnswerStuByQuest_id(questid);
        moduleTestAnswerStuRepository.deleteAll(list);
    }

    @Override
    public int findByMid(int m_id) {
        return expModelRepository.findByM_id(m_id);
    }

    @Override
    public ExpModel findExpModelsByKaoheMid(int mid) {
        return expModelRepository.findExpModelsByKaoheMid(mid);
    }


}
