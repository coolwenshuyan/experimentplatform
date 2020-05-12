package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
public interface TotalScoreCurrentRepository extends BaseRepository<TotalScoreCurrent, Integer>, JpaSpecificationExecutor<TotalScoreCurrent> {

}
