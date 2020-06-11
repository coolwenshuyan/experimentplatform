package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.*;
import com.coolwen.experimentplatform.model.*;
import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.model.DTO.KaoheModelAndExpInfoDTO;
import com.coolwen.experimentplatform.model.DTO.StudentVo;
import com.coolwen.experimentplatform.specification.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("kaoheModelService")
public class KaoheModelServiceImpl implements KaoheModelService {

    @Autowired
    private KaoheModelRepository kaoheModelRepository;
    @Autowired
    ExpModelRepository expModelRepository;

    @Autowired
    KaoHeModelScoreRepository kaoHeModelScoreRepository;

    @Autowired
    TotalScoreCurrentRepository totalScoreCurrentRepository;

    @Autowired
    ModuleTestQuestRepository moduleTestQuestRepository;

    @Autowired
    ModuleTestAnswerStuRepository moduleTestAnswerStuRepository;


    @Override
    public void add(KaoheModel kaoheModel) {
        kaoheModelRepository.save(kaoheModel);

    }

    @Override
    public void update(KaoheModel kaoheModel) {
        kaoheModelRepository.save(kaoheModel);

    }

    @Override
    public void delete(int id) {
        kaoheModelRepository.deleteById(id);
    }

    @Override
    public List<KaoheModel> listKaoheModel() {
        return kaoheModelRepository.findAll();
    }

    @Override
    public KaoheModel findById(int id) {
        return kaoheModelRepository.findbyid(id);
    }

    @Override
    public Page<KaoheModel> findAll(Pageable pageable) {
        return kaoheModelRepository.findAll(pageable);
    }

//    @Override
//    public boolean isItInKaohe(int mid) {
//        int countIn=kaoheModelRepository.countAllByM_id(mid);
//        if (countIn>0){
//            return true;
//        }else {
//            return false;
//        }
//    }

    @Override
    public List<Integer> inKaoheList() {
        return kaoheModelRepository.findAllMid();
    }

    @Override
    public long countKaoheModel() {
        return kaoheModelRepository.count();
    }

    @Override
    public List<KaoheModel> findAll() {
        return kaoheModelRepository.findAll();
    }

    @Override
    public Integer findKaoheNum() {
        return kaoheModelRepository.findKaoheNum();
    }


    @Override
    public Page<KaoHeModelStuDTO> findKaoheModelStuDto(int stu_id, int pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum, 6);
        return kaoHeModelScoreRepository.findKaoHeModelStuDTOByStuId(stu_id, pageRequest);
    }

    @Override
    public KaoheModel findKaoheModelByMid(int mid) {
        return kaoheModelRepository.findKaoheModelByMid(mid);
    }

    @Override
    public void deleteKaoHeModuleByMid(KaoheModel kaoheModel) {
        kaoheModelRepository.delete(kaoheModel);
    }

    @Override
    public void deleteByMid(int mid) {

        KaoheModel km = kaoheModelRepository.findKaoheModelByMid(mid);
        List<KaoHeModelScore> khms = kaoHeModelScoreRepository.findKaoHeModelScoreByKaoheid(km.getId());
        // 移除模块时，删除表12中该模块学生成绩记录，更新13模块数量
        for (KaoHeModelScore i : khms){
            TotalScoreCurrent tsc = totalScoreCurrentRepository.findTotalScoreCurrentByStuId(i.getStuId());
            tsc.setKaoheNum(tsc.getKaoheNum()-1);
            totalScoreCurrentRepository.save(tsc);
            kaoHeModelScoreRepository.delete(i);
        }



//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+mid);
//        KaoheModel km = kaoheModelRepository.findKaoheModelByMid(mid);
//        System.out.println("km000000000"+km);
//
//        // 整体理论测试占比和模块考核成绩占比存放在-1的记录中(!!已经废除!!)
////        KaoheModel akm = kaoheModelRepository.findKaoheModelByMid(-1);
////        System.out.println("akm000000000"+akm);
//
//        List<KaoHeModelScore> khms = kaoHeModelScoreRepository.findKaoHeModelScoreByKaoheid(km.getId());
//
//        System.out.println("khms000000000"+khms);
//
//        // 遍历该模块所有学生的模块成绩,进行修改
//        for (KaoHeModelScore i : khms){
//            System.out.println(">>>>>>>>>"+i);
//            TotalScoreCurrent tsc = totalScoreCurrentRepository.findTotalScoreCurrentByStuId(i.getStuId());
//            System.out.println(">>>>>>>>>"+tsc);
//            tsc.setKaoheNum(tsc.getKaoheNum()-1);
//            float msc = i.getmScore();
//            float mtsc = tsc.getmTotalScore();
//            System.out.println("mtsc>>>>>>>>>>>>>>>>>>>>>>>>>>"+mtsc);
//            float newmtsc = mtsc - msc * km.getM_scale();
//            System.out.println("newmtsc>>>>>>>>>>>>>>>>>>>>>>>>>>"+newmtsc);
//            tsc.setmTotalScore(newmtsc);
//
//            float old_total_score = tsc.getTotalScore();
//            float new_total_score = old_total_score - newmtsc*km.getKaohe_baifenbi();
//            tsc.setmTotalScore(newmtsc);
//            tsc.setTotalScore(new_total_score);
//            totalScoreCurrentRepository.save(tsc);
//
//            kaoHeModelScoreRepository.delete(i);
//        }



    }

    @Override
    public void updateAllGreatestWeight(float kaoheBaifenbi, float testBaifenbi) {
        kaoheModelRepository.updateAllGreatestWeight(kaoheBaifenbi,testBaifenbi);
    }

    @Override
    public void deleteMTestAnswerByMid(int mid) {
        List<ModuleTestQuest> moduleTestQuests = moduleTestQuestRepository.findAllByMid(mid);

        for(ModuleTestQuest i : moduleTestQuests){
//            System.out.println("ModuleTestQuest>>>>>>>>>>>>>>"+i.getQuestId());
            moduleTestAnswerStuRepository.deleteAllByQuest_id(i.getQuestId());
        }

    }

    @Override
    public KaoheModelAndExpInfoDTO findKaoheModelAndExpInfoDTOByKaoheid(int kaoheid) {
        return kaoheModelRepository.findKaoheModelAndExpInfoDTOByKaoheid(kaoheid);
    }

    @Override
    public Page<KaoheModelAndExpInfoDTO> findAllKaoheModelAndExpInfoDTO(int pageNum) {
        Pageable pageable  = PageRequest.of(pageNum,10);
        return kaoheModelRepository.findAllKaoheModelAndExpInfoDTO(pageable);
    }



}
