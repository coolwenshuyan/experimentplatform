package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.ExpModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpModelService {
    public void save(ExpModel expModel);

    public ExpModel findExpModelByID(int id);

    public void deleteExpModelById(int id);

    public Page<ExpModel> findModelList(int pageNum);

    public List<ExpModel> findExpModelsBym_name(String m_name);

    public List<ExpModel> findAll();

    public Page<ExpModel> finExpAll(int pageNum);
}
