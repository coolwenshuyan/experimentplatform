package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.model.DTO.KaoheModelAndExpInfoDTO;
import com.coolwen.experimentplatform.model.KaoheModel;
import com.coolwen.experimentplatform.specification.SimplePageBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KaoheModelRepository extends BaseRepository<KaoheModel, Integer>, JpaSpecificationExecutor<KaoheModel> {

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

    @Query("select k.m_id from KaoheModel as k")
    List<Integer> findAllMid();

    @Query("select count(k) from KaoheModel k")
    Integer findKaoheNum();

    @Query("select a.id from KaoheModel a where a.m_id = ?1")
    Integer findByMid(int mid);

    @Query("select a from KaoheModel a where a.m_id = ?1")
    KaoheModel findKaoheModelByMid(int mid);

    @Modifying
    @Transactional(readOnly = false)
    @Query("update KaoheModel k set k.kaohe_baifenbi= ?1,k.test_baifenbi= ?2")
    void updateAllGreatestWeight(float kaoheBaifenbi,float testBaifenbi);

    @Query("select new com.coolwen.experimentplatform.model.DTO.KaoheModelAndExpInfoDTO " +
            "(khm.id,khm.m_id,khm.m_order,khm.m_scale,khm.m_test_baifenbi,khm.m_report_baifenbi,em.m_name,em.classhour,em.purpose,em.m_type) " +
            "from KaoheModel khm left join ExpModel em " +
            "on khm.m_id = em.m_id " +
            "where khm.id = ?1")
    KaoheModelAndExpInfoDTO findKaoheModelAndExpInfoDTOByKaoheid(int Kaoheid);


    @Query("select new com.coolwen.experimentplatform.model.DTO.KaoheModelAndExpInfoDTO " +
            "(khm.id,khm.m_id,khm.m_order,khm.m_scale,khm.m_test_baifenbi,khm.m_report_baifenbi,em.m_name,em.classhour,em.purpose,em.m_type) " +
            "from KaoheModel khm left join ExpModel em " +
            "on khm.m_id = em.m_id ")
    Page<KaoheModelAndExpInfoDTO> findAllKaoheModelAndExpInfoDTO(Pageable pageable);





}
