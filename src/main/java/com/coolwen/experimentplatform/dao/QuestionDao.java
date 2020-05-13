package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question,Integer> {

    //    @Query("select t from t_question t where t.id = ?1")
    @Query(value="select content from t_question where id = ?",nativeQuery=true)
    public String findByquestioncontent(int id);


//    @Query(value="select * from t_question",nativeQuery=true)
//    public Question getAll();

    @Query(value="select * from t_question where id = ?",nativeQuery=true)
    public Question findById(int id);

}
