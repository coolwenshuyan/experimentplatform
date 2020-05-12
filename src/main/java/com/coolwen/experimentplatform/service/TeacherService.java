package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Teacher;

public interface TeacherService {
    void add(Teacher teacher);

    void delete(int id);

    void updata(Teacher teacher);

    Teacher findById(int id);




}
