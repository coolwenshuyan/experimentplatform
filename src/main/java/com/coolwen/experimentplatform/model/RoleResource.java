package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author CoolWen
 * @version 2018-10-31 10:00
 */
@Entity
@Table(name="t_role_res")
public class RoleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "role_resource_id")
    @TableGenerator(name = "role_resource_id", initialValue = 0,allocationSize = 1,table = "seq_table")
    private int id;
    private int roleId;
    private int resId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
