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

    @Query("delete from KaoHeModelScore khms where khms.tKaohemodleId = ?1 ")
    void deleteByTKaohemodleId(int tkid);

    @Query("select new com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO(kh.m_id,khs.stuId,khs.mTeststate,khs.mReportstate,khs.mScale,khs.mScore,e.m_name,e.imageurl,e.m_inurl) from KaoheModel kh left join ExpModel e on kh.m_id = e.m_id left join KaoHeModelScore khs on khs.tKaohemodleId = kh.id where khs.stuId = ?1")
    Page<KaoHeModelStuDTO> findKaoHeModelStuDTOByStuId (int stu_id, PageRequest pageRequest);

    @Query("select new com.coolwen.experimentplatform.model.DTO.KaoHeModelStuDTO(kh.m_id,khs.stuId,khs.mTeststate,khs.mReportstate,khs.mScale,khs.mScore,e.m_name,e.imageurl,e.m_inurl) from KaoheModel kh left join ExpModel e on kh.m_id = e.m_id left join KaoHeModelScore khs on khs.tKaohemodleId = kh.id where khs.stuId = ?1 and e.m_id = ?2")
    KaoHeModelStuDTO findKaoHeModelStuDTOByStuId (int stu_id,int mid);

    @Query("select k from KaoHeModelScore k where k.tKaohemodleId=?1 and k.stuId = ?2")
    KaoHeModelScore findByStuIdAndTKaohemodleId(int kaoHeModleId,int stu);

    @Query("select khs from KaoHeModelScore khs,Student s,ClassModel cm where khs.stuId = s.id and s.classId = cm.classId and cm.classIscurrent = false and khs.tKaohemodleId = ?1")
    List<KaoHeModelScore> findKaoHeModelScoreByTKaohemodleIdAndStuId(int kaoheid);

    @Query("select khs from KaoHeModelScore khs where khs.tKaohemodleId = ?1")
    List<KaoHeModelScore> findKaoHeModelScoreByKaoheid(int kaoheid);

    @Query("select khs from KaoHeModelScore khs where khs.stuId = ?1")
    List<KaoHeModelScore> findKaoHeModelScoreByStuId(int id);

    @Query("select k from KaoHeModelScore k where k.stuId = ?1 and k.tKaohemodleId = ?2")
    KaoHeModelScore findKaoHeModelScoreByStuIdAndId(int stuid,int id);
//

}
