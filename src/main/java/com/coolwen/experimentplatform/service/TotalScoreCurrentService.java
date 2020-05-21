package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.DTO.ModuleGradesDto;
import com.coolwen.experimentplatform.model.KaoHeModelScore;
import com.coolwen.experimentplatform.model.TotalScoreCurrent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Artell
 * @date 2020/5/12 18:04
 */
public interface TotalScoreCurrentService {
    public void add(TotalScoreCurrent res);

    public void update(TotalScoreCurrent res);

    public void delete(int id);

    public TotalScoreCurrent load(int id);

    public List<TotalScoreCurrent> listTotalScoreCurrent();

    public List<TotalScoreCurrent> findeAllBystuid(int id);

    List<ModuleGradesDto> ModuleGrade(int id);

    TotalScoreCurrent findTotalScoreCurrentByStuID(int stuId);

    TotalScoreCurrent findTotalScoreCurrentByStuId(int stuid);
}
