package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.KaoheModelRepository;
import com.coolwen.experimentplatform.model.KaoheModel;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<KaoheModel> listKaoheModel() {
        return null;
    }
}
