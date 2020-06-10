package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.DTO.QuestAnswerDto;
import com.coolwen.experimentplatform.model.DTO.QuestListAnswerDto;
import com.coolwen.experimentplatform.model.ExpModel;
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

//    添加模块测试题
    void addModuleTestQuest(ModuleTestQuest moduleTestQuest);

//    删除添加模块测试题
    void deleteQuest(int questId);

//    public void updateQuest(int questId);

//    通过模块测试题的题目和模块测试题的模块id查找全部模块测试题
    List<ModuleTestQuest> load(String questDescribe, int mId);

//    通过问题id查找该问题的信息
    ModuleTestQuest findQuestByQuestId(int questId);

//    public List<ModuleTestQuest> loadAll();

//    String findByQuestDescribes(String questDescribe);

//    通过模块id找到所有的模块测试题
    List<ModuleTestQuest> find(int mId);

//    通过问题id找到所有的模块测试题
    List<ModuleTestQuest> findAllByQuestId(int questId);

//    int countAllByQuestId();

//    通过问题的题目查找该问题的信息
    ModuleTestQuest findByQuestDescribe(String questDescribe);

//    public Page<ModuleTestQuest> findByPage(Pageable pageable,int mId);

    public List<QuestAnswerDto> loadQuestAnswerDto(int mId, String type);

    public List<QuestListAnswerDto> listQuestAnswerDto(String type, int mId);

//    public List<ModuleTestQuest> findAllByMId(int mId);

//    以mid为条件分页
    Page<ModuleTestQuest> findByLastPage(Pageable pageable,int mId);

    void deleteAllModuleTestQuest(List<ModuleTestQuest> list);

//    考核模块和模块测试题两张表，联合查询，通过mid分页
    Page<ModuleTestQuest> findByExpPage(int mId, Pageable pageable);

}
