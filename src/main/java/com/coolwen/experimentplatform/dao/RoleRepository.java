package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author CoolWen
 * @version 2018-10-31 8:25
 */
public interface RoleRepository extends BaseRepository<Role, Integer>, JpaSpecificationExecutor<Role> {
}
