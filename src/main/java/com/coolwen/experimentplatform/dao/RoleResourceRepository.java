package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.RoleResource;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author CoolWen
 * @version 2018-11-02 10:15
 */
public interface RoleResourceRepository extends BaseRepository<RoleResource, Integer>, JpaSpecificationExecutor<RoleResource> {
    RoleResource findByRoleIdAndResId(int roleId, int resId);
}
