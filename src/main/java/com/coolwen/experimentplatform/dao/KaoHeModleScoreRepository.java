package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.KaoHeModleScore;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 15:44
 * @Description: TODO
 */
public interface KaoHeModleScoreRepository extends BaseRepository<KaoHeModleScore, Integer>, JpaSpecificationExecutor<KaoHeModleScore> {
}
