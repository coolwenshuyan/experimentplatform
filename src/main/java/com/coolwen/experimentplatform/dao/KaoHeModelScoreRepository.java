package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
public interface KaoHeModelScoreRepository extends BaseRepository<KaoHeModelScore, Integer>, JpaSpecificationExecutor<KaoHeModelScore> {

    @Query("delete from KaoHeModelScore where tKaohemodleId=?1")
    public void deleteByTKaohemodleId(int tkid);

    @Query("select new com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO(kh.m_id,khs.stuId,khs.mTeststate,khs.mReportstate,khs.mScale,khs.mScore,e.m_name,e.imageurl) from KaoheModel kh left join ExpModel e on kh.m_id = e.m_id left join KaoHeModelScore khs on khs.tKaohemodleId = kh.id where khs.stuId = ?1")
    Page<KaoHeModelStuDTO> findKaoHeModelStuDTOByStuId (int stu_id, PageRequest pageRequest);


//
}
