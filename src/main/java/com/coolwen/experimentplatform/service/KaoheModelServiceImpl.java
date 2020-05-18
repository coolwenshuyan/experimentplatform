package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.ExpModelRepository;
import com.coolwen.experimentplatform.dao.KaoHeModelScoreRepository;
import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.model.KaoheModel;
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
        return  kaoheModelRepository.findbyid(id);
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
        PageRequest pageRequest = PageRequest.of(pageNum,6);
        return kaoHeModelScoreRepository.findKaoHeModelStuDTOByStuId(stu_id,pageRequest);
    }

    @Override
    public KaoheModel findKaoheModelByMid(int mid) {
        return kaoheModelRepository.findKaoheModelByMid(mid);
    }

}
