package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Resource;

import java.util.List;

/**
 * @author CoolWen
 * @version 2018-11-02 6:34
 */
public interface ResourceService {
    public void add(Resource res);

    public void update(Resource res);

    public void delete(int id);

    public Resource load(int id);

    public List<Resource> listResource();
}
