package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Docker;

import java.awt.print.Pageable;
import java.util.List;

public interface DockerService {

    List<Docker> findDockersByTenData();

}
