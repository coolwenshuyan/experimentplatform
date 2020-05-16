package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
public interface KaoHeModelScoreRepository extends BaseRepository<KaoHeModelScore, Integer> {

    @Query("delete from KaoHeModelScore where tKaohemodleId=?1")
    public void deleteByTKaohemodleId(int tkid);


}
