package com.coolwen.experimentplatform.service;

import com.coolwen.experimentplatform.dao.UserRepository;
import com.coolwen.experimentplatform.dao.UserRoleRepository;
import com.coolwen.experimentplatform.kit.ShiroKit;
import com.coolwen.experimentplatform.model.Resource;
import com.coolwen.experimentplatform.model.Role;
import com.coolwen.experimentplatform.model.User;
import com.coolwen.experimentplatform.model.UserRole;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CoolWen
 * @version 2018-10-31 10:46
 */
@Service
public class UserServiceImpl implements UserService {


    protected static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User add(User user) {
        if (ShiroKit.isEmpty(user.getUsername()) || ShiroKit.isEmpty(user.getPassword())) {
            throw new RuntimeException("用户名或者密码不能为空！");
        }
        User u = userRepository.findByUsername(user.getUsername());
        if (u != null) {
            throw new RuntimeException("用户名已经存在!");
        }
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User add(User user, List<Integer> rids) {
        this.add(user);
        for (int rid : rids) {
            UserRole ur = new UserRole();
            ur.setRoleId(rid);
            ur.setUserId(user.getId());
            userRoleRepository.save(ur);
        }
        return user;
    }

    @Override
    public void delete(int id) {
        User u = this.load(id);
        userRepository.delete(u);
    }

    @Override
    @Transactional
    public User update(User user, List<Integer> rids) {
        logger.debug("user:" + user.toString());
        userRoleRepository.deleteByUserId(user.getId());
        for (int rid : rids) {
            UserRole ur = new UserRole();
            ur.setUserId(user.getId());
            ur.setRoleId(rid);
            userRoleRepository.save(ur);
        }
        userRepository.save(user);
        //这里制造个异常，测是事务管理是否生效
        //      int i = 1 / 0;
        return user;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User load(int id) {
        User u = userRepository.findById(id).get();
        return u;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        User u = userRepository.findByUsername(username);
        if (u == null) throw new UnknownAccountException("用户名或者密码出错");
        if (!u.getPassword().equals(ShiroKit.md5(password, username)))
            throw new IncorrectCredentialsException("用户名或者密码出错");
        if (u.getStatus()) throw new LockedAccountException("用户已经被锁定");
        return u;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listByRole(int roleId) {
        return userRepository.listByRole(roleId);
    }

    @Override
    public List<Resource> listAllResource(int uid) {
        return userRepository.listAllResource(uid);
    }

    @Override
    public List<String> listRoleSnByUser(int uid) {
        return userRepository.listRoleSnByUser(uid);
    }

    @Override
    public List<Role> listUserRole(int uid) {
        return userRepository.listUserRole(uid);
    }
}
