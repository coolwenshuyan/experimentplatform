package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.model.Resource;

import java.util.List;

public interface KaoheModelService {
    //添加
    public void add(KaoheModel kaoheModel);
    //修改
    public void update(KaoheModel kaoheModel);

    public List<KaoheModel> listKaoheModel();
    public KaoheModel findById(int id);

}
