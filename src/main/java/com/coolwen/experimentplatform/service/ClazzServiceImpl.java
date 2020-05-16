package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ClazzRepository;
import com.coolwen.experimentplatform.model.ClassModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    ClazzRepository clazzRepository;
    @Override
    public Page<ClassModel> findClassList(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,10);
        return clazzRepository.findAll(pageable);
    }

    @Override
    public ClassModel findById(int id) {
        return clazzRepository.findById(id).get();
    }

    @Override
    public void saveClazz(ClassModel clazz) {
        clazzRepository.save(clazz);
    }

    @Override
    public void deleteClazz(int id) {
        ClassModel clazz = findById(id);
        clazzRepository.delete(clazz);
    }

    @Override
    public List<ClassModel> findAllClass() {
        return clazzRepository.findAll();
    }


}
