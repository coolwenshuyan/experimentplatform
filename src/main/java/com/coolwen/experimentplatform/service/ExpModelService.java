package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ExpModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpModelService {
    public void save(ExpModel expModel);

    public ExpModel findExpModelByID(int id);

    public void deleteExpModelById(int id);

    public Page<ExpModel> findModelList(int pageNum);
}
