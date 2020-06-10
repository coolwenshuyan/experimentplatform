package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.dao.QuestListAnswerRepositoryCustom;
import com.coolwen.experimentplatform.model.DTO.QuestAnswerDto;
import com.coolwen.experimentplatform.model.DTO.QuestListAnswerDto;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 14:30
 */
@Service
public class ModuleTestQuestServiceImpl implements ModuleTestQuestService {

    @Autowired
    private ModuleTestQuestRepository questRepository;

    @Autowired
    private QuestListAnswerRepositoryCustom questListAnswerRepositoryCustom;


    @Override
    public void addModuleTestQuest(ModuleTestQuest moduleTestQuest) {
        questRepository.save(moduleTestQuest);
    }

    @Override
    public void deleteQuest(int questId) {
        questRepository.deleteById(questId);
    }

//    @Override
//    public void updateQuest(int questId) {
//        questRepository.findById(questId);
//    }
//

    @Override
    public List<ModuleTestQuest> load(String questDescribe, int mId) {
        return questRepository.findAllByQuestDescribeAndMId(questDescribe, mId);
    }

    @Override
    public ModuleTestQuest findQuestByQuestId(int questId) {
        ModuleTestQuest quest = new ModuleTestQuest();
        quest = questRepository.findQuestByQuestId(questId);
        return quest;
    }

//    @Override
//    public List<ModuleTestQuest> loadAll() {
//        return questRepository.findAll();
//    }
//
//    @Override
//    public String findByQuestDescribes(String questDescribe) {
//        return questRepository.findByQuestDescribes(questDescribe);
//    }

    @Override
    public List<ModuleTestQuest> find(int mId) {
        return questRepository.findAllByMid(mId);
    }

    @Override
    public List<ModuleTestQuest> findAllByQuestId(int questId) {
        return questRepository.findAllByQuestId(questId);
    }

//    @Override
//    public int countAllByQuestId() {
//        return questRepository.countAllByQuestId();
//    }

    @Override
    public ModuleTestQuest findByQuestDescribe(String questDescribe) {
        return questRepository.findByQuestDescribe(questDescribe);
    }

//    @Override
//    public Page<ModuleTestQuest> findByPage(Pageable pageable,int mId) {
//        return questRepository.findAll(pageable);
//    }
//
//    @Override
//    public List<ModuleTestQuest> findAllByMId(int mId) {
//        return questRepository.findAllByMid(mId);
//    }
//

    @Override
    public List<QuestAnswerDto> loadQuestAnswerDto(int mId, String type) {
        return questRepository.findQuestAnswerByMid(mId, type);
    }

    @Override
    public List<QuestListAnswerDto> listQuestAnswerDto(String type, int mId) {
        return questListAnswerRepositoryCustom.listQuestAnswerDto(type, mId);
    }

    @Override
    public Page<ModuleTestQuest> findByLastPage(Pageable pageable ,int mId) {
        return questRepository.findTermQuest(mId,pageable);
    }

    @Override
    public void deleteAllModuleTestQuest(List<ModuleTestQuest> list) {
        questRepository.deleteAll(list);
    }


    @Override
    public Page<ModuleTestQuest> findByExpPage(int mId, Pageable pageable) {
        return questRepository.findByExpPage(mId,pageable);
    }

}
