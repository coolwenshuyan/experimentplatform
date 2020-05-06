package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.model.Resource;
import com.coolwen.experimentplatform.model.Role;
import com.coolwen.experimentplatform.model.User;

import java.util.List;

/**
 * @author CoolWen
 * @version 2018-10-31 10:40
 */

public interface UserService {

    public User add(User user);

    public User add(User user, List<Integer> rids);

    public void delete(int id);



    public User update(User user, List<Integer> rids);

    public User update(User user);

    public User load(int id);

    public User findByUsername(String username);

    public User login(String username, String password);

    public List<User> list();

    public List<User> listByRole(int roleId);

    public List<Resource> listAllResource(int uid);

    public List<String> listRoleSnByUser(int uid);

    public List<Role> listUserRole(int uid);
}
