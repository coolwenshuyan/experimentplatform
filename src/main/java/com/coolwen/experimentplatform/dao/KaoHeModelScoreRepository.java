package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 15:44
 * @Description: TODO
 */
public interface KaoHeModelScoreRepository extends BaseRepository<KaoHeModelScore, Integer>, JpaSpecificationExecutor<KaoHeModelScore> {
}
