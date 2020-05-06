package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.UserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CoolWen
 * @version 2018-10-31 8:25
 */

public interface UserRoleRepository extends BaseRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole> {

    UserRole findByUserIdAndRoleId(int uid, int roleId);

    //    @Modifying
//    @Query("delete from UserRole where userId=?1")
    @Transactional
    public void deleteByUserId(int uid);
    //    @Modifying
}
