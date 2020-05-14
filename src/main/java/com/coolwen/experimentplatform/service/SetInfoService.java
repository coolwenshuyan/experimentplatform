package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.SetInfo;

public interface SetInfoService {


    void add(SetInfo setInfo);

    SetInfo findById(int i);
}
