package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Docker;
import java.util.List;

public interface DockerService {

    List<Docker> findDockersByTenData();

    Docker findDockerByDc_url(String url);

    void addDocker(Docker docker);
    Docker findDockerByStu_id(int stuid);



}
