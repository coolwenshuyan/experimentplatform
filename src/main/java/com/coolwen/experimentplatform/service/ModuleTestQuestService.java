package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.DTO.QuestAnswerDto;
import com.coolwen.experimentplatform.model.DTO.QuestListAnswerDto;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:08
 */
public interface ModuleTestQuestService {

    public void addModuleTestQuest(ModuleTestQuest moduleTestQuest);

    public void deleteQuest(int questId);

    public void updateQuest(int questId);

    public List<ModuleTestQuest> load(String questDescribe, int mId);

    public ModuleTestQuest findQuestByQuestId(int questId);

    public List<ModuleTestQuest> loadAll();

    String findByQuestDescribes(String questDescribe);

    List<ModuleTestQuest> find(int mId);

    List<ModuleTestQuest> findAllByQuestId(int questId);

    int countAllByQuestId();

    ModuleTestQuest findByQuestDescribe(String questDescribe);

    public Page<ModuleTestQuest> findByPage(Pageable pageable);

    public List<QuestAnswerDto> loadQuestAnswerDto(int mId, String type);

    public List<QuestListAnswerDto> listQuestAnswerDto(String type, int mId);

    public List<ModuleTestQuest> findAllByMId(int mId);

    Page<ModuleTestQuest> findByLastPage(Pageable pageable,int mId);

    void deleteAllModuleTestQuest(List<ModuleTestQuest> list);

}
