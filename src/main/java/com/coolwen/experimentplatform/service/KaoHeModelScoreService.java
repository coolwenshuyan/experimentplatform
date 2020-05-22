package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.KaoHeModelScore;
import com.coolwen.experimentplatform.model.KaoheModel;

import java.util.List;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
public interface KaoHeModelScoreService {

    public void add(KaoHeModelScore res);

    public void update(KaoHeModelScore res);

    public void delete(int id);

    public void deleteAllByKaohemId(Integer kaohemid);

    public void deleteByStuId(int sid);

    public KaoHeModelScore load(int id);

    public List<KaoHeModelScore> listKaoHeModleScore();

    KaoHeModelScore findKaoheModelScoreByMid(int mid,int stu);

    List<KaoHeModelScore> findKaoHeModelScoreByTKaohemodleIdAndStuId(int kaohemodeleid);

    void deleteAllKaohe(List<KaoHeModelScore> kaoHeModelScores);


}
