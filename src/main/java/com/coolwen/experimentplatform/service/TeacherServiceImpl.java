package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.TeacherRepository;
import com.coolwen.experimentplatform.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public void add(Teacher teacher) {
        teacherRepository.save(teacher);

    }

    @Override
    public void delete(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void updata(Teacher teacher) {

    }

    @Override
    public Teacher findById(int id) {
        Teacher teacher = new Teacher();
        teacher = teacherRepository.findTeacherById(id);
        return teacher;

    }
}
