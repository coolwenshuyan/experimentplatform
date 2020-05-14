package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.ExpKaohe;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KaoheModelRepository extends BaseRepository<KaoheModel,Integer> , JpaSpecificationExecutor<KaoheModel> {

//    static void sava(KaoheModel kaoheModel) {
//    }

    //自己写的查询语句
    @Query("select u from KaoheModel u where u.id = ?1") //这里的User对应Model层
    public KaoheModel findbyid(int id);

//
//
//    @Query("select u from User u")
//    public List<KaoheModel> list();


    @Query("select res from Role role,Resource res,RoleResource rr where " +
            "role.id=rr.roleId and res.id=rr.resId and role.id=?1")
    List<KaoheModel> listKaoheModel(int mId);


//    @Query("select ExpKaohe from ExpModel em,KaoheModel km where em.m_id=km.id")
//    List<ExpKaohe> loadallmodel(int mId);

//    int countAllByM_id(int mid);
}
