package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.model.KaoheModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("kaoheModelService")
public class KaoheModelServiceImpl implements KaoheModelService {

    @Autowired
    private KaoheModelRepository kaoheModelRepository;

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
}
