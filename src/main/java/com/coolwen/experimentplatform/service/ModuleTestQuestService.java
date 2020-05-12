package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ModuleTestQuest;

import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 12:08
 */
public interface ModuleTestQuestService {

    public void addModuleTestQuest(ModuleTestQuest moduleTestQuest);

    public void deleteQuest(int questId);

    public void updateQuest(int questId);

    public List<ModuleTestQuest> load(String questDescribe);

    public ModuleTestQuest update(int questId);

    public List<ModuleTestQuest> loadAll();
}
