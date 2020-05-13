package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
