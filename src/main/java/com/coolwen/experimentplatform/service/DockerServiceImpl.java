package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.DockerRepository;
import com.coolwen.experimentplatform.model.Docker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerServiceImpl implements DockerService {
    @Autowired
    DockerRepository dockerRepository;
    @Override
    public List<Docker> findDockersByTenData() {
        return dockerRepository.findDockersByTenData( PageRequest.of(0,6));
    }

    @Override
    public Docker findDockerByDc_url(String url) {
        return dockerRepository.findDockerByDc_url(url);
    }

    @Override
    public void addDocker(Docker docker) {
        dockerRepository.save(docker);
    }

    @Override
    public Docker findDockerByStu_id(int stuid) {
        return dockerRepository.findDockerByStu_id(stuid);
    }
}
