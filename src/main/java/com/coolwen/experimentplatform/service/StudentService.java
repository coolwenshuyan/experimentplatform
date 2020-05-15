package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Artell
 * @version 2020/5/15 21:30
 */


public interface StudentService {
    Page<Student> findAll(int pageNum);
}
