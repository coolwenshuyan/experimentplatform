package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KaoheModelService {
    //添加
    public void add(KaoheModel kaoheModel);
    //修改
    public void update(KaoheModel kaoheModel);

    public void delete(int id);

    public List<KaoheModel> listKaoheModel();

    public KaoheModel findById(int id);

    Page<KaoheModel> findAll(Pageable pageable);

//    boolean isItInKaohe(int mid);

    public List<Integer> inKaoheList();

    public long countKaoheModel();

    List<KaoheModel> findAll();

    Integer findKaoheNum();

    Page<KaoHeModelStuDTO> findKaoheModelStuDto(int stu_id,int pageNum);

    KaoheModel findKaoheModelByMid(int mid);

    void deleteKaoHeModuleByMid(KaoheModel kaoheModel);



}
