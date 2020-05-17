package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:08
 */
public interface ModuleTestQuestService {

    public void addModuleTestQuest(ModuleTestQuest moduleTestQuest);

    public void deleteQuest(int questId);

    public void updateQuest(int questId);

    public List<ModuleTestQuest> load(String questDescribe,int mId);

    public ModuleTestQuest findQuestByQuestId(int questId);

    public List<ModuleTestQuest> loadAll();

    String findByQuestDescribes(String questDescribe);

    List<ModuleTestQuest> find(int mId);

    List<ModuleTestQuest> findAllByQuestId(int questId);

    int countAllByQuestId();

    ModuleTestQuest findByQuestDescribe(String questDescribe);

    Page<ModuleTestQuest> findByPage(ModuleTestQuest moduleTestQuest, Pageable pageable);
}
