package com.coolwen.experimentplatform.model.DTO;

import javax.persistence.*;

/**
 * @author Artell
 * @version 2020/6/9 20:23
 */


public class OneModelScoreDTO {
    private String modelName;
    private float mTestScore;
    private float mtestBaiFenBi;
    private float mReportScore;
    private float mReportBaiFenBi;
    private float mscale;

    public OneModelScoreDTO(String modelName, float mTestScore, float mtestBaiFenBi, float mReportScore, float mReportBaiFenBi, float mscale) {
        this.modelName = modelName;
        this.mTestScore = mTestScore;
        this.mtestBaiFenBi = mtestBaiFenBi;
        this.mReportScore = mReportScore;
        this.mReportBaiFenBi = mReportBaiFenBi;
        this.mscale = mscale;
    }



    @PrePersist
    void prePersist(Object object) {
    }

    @PreUpdate
    void preUpdate(Object object) {
    }

    @PreRemove
    void preRemove(Object object) {
    }

    @PostLoad
    void postLoad(Object object) {
    }

    @PostRemove
    void postRemove(Object object) {
    }

    @PostUpdate
    void postUpdate(Object object) {
    }

    @PostPersist
    void postPersist(Object object) {
    }

    @Override
    public String toString() {
        return "OneModelScoreDTO{" +
                "modelName='" + modelName + '\'' +
                ", mTestScore=" + mTestScore +
                ", mtestBaiFenBi=" + mtestBaiFenBi +
                ", mReportScore=" + mReportScore +
                ", mReportBaiFenBi=" + mReportBaiFenBi +
                ", mscale=" + mscale +
                '}';
    }
}
