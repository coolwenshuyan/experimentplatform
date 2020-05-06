package com.coolwen.experimentplatform.model;

import javax.persistence.*;

/**
 * @author CoolWen
 * @version 2018-10-31 6:50
 */
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_id")
    @TableGenerator(name = "user_id", initialValue = 0, allocationSize = 1,table = "seq_table")
    private int id;
    private String username;
    private String nickname;
    private String password;
    private Boolean status;

    public User() {
    }

    public User(String username, String nickname, String password, Boolean status) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
