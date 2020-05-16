package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ClassModel;


import org.springframework.data.jpa.repository.Query;

public interface ClazzRepository extends BaseRepository<ClassModel,Integer> {

    @Query("select  c from ClassModel c where c.className=?1")
    ClassModel findClazzByClass_name(String class_name);
}
