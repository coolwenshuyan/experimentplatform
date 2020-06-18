package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.DockerRepository;
import com.coolwen.experimentplatform.model.Docker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Docker> findAll(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,10);
        return dockerRepository.findAll(pageable);
    }

    @Override
    public Docker findByid(int id) {
        return dockerRepository.findById(id).get();
    }

    @Override
    public void delDocker(int id) {
        Docker docker = findByid(id);
        dockerRepository.delete(docker);
    }
}
