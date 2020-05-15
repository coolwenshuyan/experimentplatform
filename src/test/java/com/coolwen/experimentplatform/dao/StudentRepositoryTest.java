package com.coolwen.experimentplatform.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ProjectName: experimentplatform
 * @Package: com.coolwen.experimentplatform.dao
 * @ClassName: StudentRepositoryTest
 * @Author: Txc
 * @Description:
 * @Date: 2020/5/15 0015 13:38
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void findAllById() {
        System.out.println(studentRepository.findAllById(1).getStuUname());
    }

    @Test
    void listStudentMTestAnswerDTO() {
        System.out.println(studentRepository.findAllByStuUname("txc").getStuUname());
    }
}