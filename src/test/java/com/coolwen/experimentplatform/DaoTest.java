package com.coolwen.experimentplatform;

import com.coolwen.experimentplatform.dao.ModuleTestAnswerRepository;
import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.dao.QuestListAnswerRepositoryCustom;
import com.coolwen.experimentplatform.model.DTO.QuestListAnswerAndStuScoreDto;
import com.coolwen.experimentplatform.model.ModuleTestAnswer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 13:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Autowired
    private QuestListAnswerRepositoryCustom questListAnswerRepositoryCustom;

    @Autowired
    private ModuleTestAnswerRepository moduleTestAnswerRepository;

    @Autowired
    private ModuleTestQuestRepository questRepository;

//
    @Test
    public void findAnswer(){
        List<QuestListAnswerAndStuScoreDto> questListAnswerAndStuScoreDtos= questListAnswerRepositoryCustom.listQuestListAnswerAndStuScoreDto("单选",1,1);
        for(QuestListAnswerAndStuScoreDto i: questListAnswerAndStuScoreDtos){
            System.out.println(i);
        }
    }
//
//    @Test
//    public void findQuestDescribe(){
//        System.out.println(questRepository.findAllByQuestDescribe("地球"));
//    }

}
