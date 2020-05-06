package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author CoolWen
 * @version 2018-10-31 8:00
 */
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "role_id")
    @TableGenerator(name = "role_id", initialValue = 0,allocationSize = 1, table = "seq_table")
    private int id;
    private String name;
    private String sn;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
