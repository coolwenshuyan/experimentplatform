package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Resource;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author CoolWen
 * @version 2018-10-31 8:25
 */
public interface ResourceRepository extends BaseRepository<Resource, Integer>, JpaSpecificationExecutor<Resource> {
    @Query("select res from Role role,Resource res,RoleResource rr where " +
            "role.id=rr.roleId and res.id=rr.resId and role.id=?1")
    List<Resource> listRoleResource(int roleId);
}
