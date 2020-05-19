package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.NewsInfo;

public interface NewsInfoService {
    void add(NewsInfo newsInfo);

    NewsInfo findById(int id);

    void delete(int id);

    int findAllmodelpeople();

    int findAllPass();
}
