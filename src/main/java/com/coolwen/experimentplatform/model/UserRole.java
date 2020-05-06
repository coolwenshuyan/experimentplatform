package com.coolwen.experimentplatform.model;

import javax.persistence.*;


/**
 * @author CoolWen
 * @version 2018-10-31 9:58
 */
@Entity
@Table(name = "t_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_role_id")
    @TableGenerator(name = "user_role_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;
    private int userId;
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
