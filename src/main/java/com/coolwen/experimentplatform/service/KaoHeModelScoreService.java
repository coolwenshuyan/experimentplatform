package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.KaoHeModleScore;
import com.coolwen.experimentplatform.model.Resource;

import java.util.List;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 17:22
 * @Description: TODO
 */
public interface KaoHeModelScoreService {
    public void add(KaoHeModleScore res);

    public void update(KaoHeModleScore res);

    public void delete(int id);

    public KaoHeModleScore load(int id);

    public List<KaoHeModleScore> listKaoHeModleScore();

}
