package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ModuleTestQuestRepository;
import com.coolwen.experimentplatform.model.ModuleTestQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 淮南
 * @date 2020/5/12 14:30
 */
@Service
public class ModuleTestQuestServiceImpl implements ModuleTestQuestService {

    @Autowired
    private ModuleTestQuestRepository questRepository;

    @Override
    public void addModuleTestQuest(ModuleTestQuest moduleTestQuest) {
        questRepository.save(moduleTestQuest);
    }

    @Override
    public void deleteQuest(int questId) {
        questRepository.deleteById(questId);
    }

    @Override
    public void updateQuest(int questId) {
        questRepository.findById(questId);
    }


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

    @Override
    public List<ModuleTestQuest> loadAll() {
        return questRepository.findAll();
    }

    @Override
    public String findByQuestDescribes(String questDescribe) {
        return questRepository.findByQuestDescribes(questDescribe);
    }

    @Override
    public List<ModuleTestQuest> find(int mId) {
        return questRepository.findAllByMid(mId);
    }

    @Override
    public List<ModuleTestQuest> findAllByQuestId(int questId) {
        return questRepository.findAllByQuestId(questId);
    }

    @Override
    public int countAllByQuestId() {
        return questRepository.countAllByQuestId();
    }

    @Override
    public ModuleTestQuest findByQuestDescribe(String questDescribe) {
        return questRepository.findByQuestDescribe(questDescribe);
    }

    @Override
    public Page<ModuleTestQuest> findByPage(ModuleTestQuest moduleTestQuest, Pageable pageable) {
        Page<ModuleTestQuest> page = null;
//　　如果moduleTestQuest的name属性为空
        if (moduleTestQuest == null) {
//　　　　　　 仅仅是分页，调用此方法
            page = questRepository.findAll(pageable);
        } else {
            Specification<ModuleTestQuest> specification = new Specification<ModuleTestQuest>() {

                //        　　　　　Root：查询哪个表
//　　　　　　　　　　CriteriaQuery：查询哪些字段，排序是什么
//　　　　　　　　　　CriteriaBuilder：字段之间是什么关系，如何生成一个查询条件，每一个查询条件都是什么方式
//　　　　　　　　　　Predicate（Expression）：单独每一条查询条件的详细描述
                @Override
                public Predicate toPredicate(Root<ModuleTestQuest> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> predicates = new ArrayList<>();
                    if (moduleTestQuest.getQuestDescribe() != null) {
                        predicates.add(cb.like(root.get("questDescribe").as(String.class), "%" + moduleTestQuest.getQuestDescribe().trim() + "%"));
                    }
                    if (predicates.size() > 0) {
                        Predicate[] predicateArr = new Predicate[predicates.size()];
                        return query.where(predicates.toArray(predicateArr)).getRestriction();
                    }
//　　　　　　　　　　这种方式使用JPA的API设置了查询条件，所以不需要再返回查询条件moduleTestQuest给Spring Data Jpa，故最后return null;即可
                    return null;
                }

            };
            page = questRepository.findAll(specification, pageable);
        }
        return page;
    }

}
