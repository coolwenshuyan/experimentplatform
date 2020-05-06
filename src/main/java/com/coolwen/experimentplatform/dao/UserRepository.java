package com.coolwen.experimentplatform.dao;

import com.coolwen.experimentplatform.dao.basedao.BaseRepository;
import com.coolwen.experimentplatform.model.Resource;
import com.coolwen.experimentplatform.model.Role;
import com.coolwen.experimentplatform.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author CoolWen
 * @version 2018-10-31 8:25
 */
public interface UserRepository extends BaseRepository<User, Integer>, JpaSpecificationExecutor<User> {
    //    public List<User> listUser();
//
    @Query("select u from User u where username=?1")
    public User findByUsername(String username);

    @Query("select res from User u,Resource res,UserRole ur,RoleResource rr where " +
            "u.id=ur.userId and ur.roleId=rr.roleId  and rr.resId=res.id and u.id=?1")
    public List<Resource> listAllResource(int uid);

    @Query("select u from User u,Role r,UserRole ur where u.id=ur.userId and r.id=ur.roleId and r.id=?1")
    public List<User> listByRole(int roleId);

    @Query("select r.sn from UserRole ur,Role r,User u where u.id=ur.userId and r.id=ur.roleId and u.id=?1")
    public List<String> listRoleSnByUser(int uid);

    @Query("select r from UserRole ur,Role r,User u where u.id=ur.userId and r.id=ur.roleId and u.id=?1")
    public List<Role> listUserRole(int uid);
}
