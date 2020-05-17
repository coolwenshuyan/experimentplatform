package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.DTO.PScoreDto;
import com.coolwen.experimentplatform.model.DTO.StudentTestScoreDTO;
import com.coolwen.experimentplatform.model.DTO.StudentVo;
import com.coolwen.experimentplatform.model.ReportAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends BaseRepository<ReportAnswer,Integer>, JpaSpecificationExecutor<ReportAnswer> {

    @Query("select new com.coolwen.experimentplatform.model.DTO.PScoreDto " +
            "(ra.reportId,r.reportDescribe,r.reportScore,ra.stuReportAnswer,ra.score) " +
            " from Report r  left join ReportAnswer ra  on r.reportId=ra.reportId " +
            "where  ra.stuId=?1 and r.mId=?2"
            )
//    Page<PScoreDto> findStudentsByStuCheckstate(Pageable pageable);
    public List<PScoreDto> listScorerDTOBystudentId(int stuId,int mid);
}
