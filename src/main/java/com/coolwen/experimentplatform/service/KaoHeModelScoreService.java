package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.KaoHeModelScore;

import java.util.List;

/**
 * @Author: Artell
 * @DateTime: 2020/5/12 17:22
 * @Description: TODO
 */
public interface KaoHeModelScoreService {
    public void add(KaoHeModelScore res);

    public void update(KaoHeModelScore res);

    public void delete(int id);

    public KaoHeModelScore load(int id);

    public List<KaoHeModelScore> listKaoHeModleScore();

}
