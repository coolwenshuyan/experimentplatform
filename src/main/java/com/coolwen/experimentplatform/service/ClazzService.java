package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ClassModel;

import org.springframework.data.domain.Page;

public interface ClazzService {

    Page<ClassModel> findClassList(int pageNum);

    ClassModel findById(int id);

    void saveClazz(ClassModel clazz);

    void deleteClazz(int id);
}
