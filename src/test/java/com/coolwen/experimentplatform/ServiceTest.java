package com.coolwen.experimentplatform;

import com.coolwen.experimentplatform.dao.ModuleTestAnswerRepository;
import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import com.coolwen.experimentplatform.service.ModuleTestQuestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 淮南
 * @date 2020/5/12 13:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private ModuleTestQuestService questService;

    @Test
    public void deleteQ(){
        questService.deleteQuest(4);
    }

//    @Test
//    public void update(){
//        ModuleTestQuest quest = new ModuleTestQuest();
//        questService.updateQuest(5);
//        quest.setQuestScore(10);
//        quest.setQuestType("多选");
//        questService.update(quest);
//    }
}
