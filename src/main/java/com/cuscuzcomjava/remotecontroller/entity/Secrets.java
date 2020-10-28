package com.cuscuzcomjava.remotecontroller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Secrets {

    @Id
    @GeneratedValue
    @Column(name = "CL_ID")
    private Long id;

    @Column(name = "CL_LOGIN")
    private String login;

    @Column(name = "CL_PASSWORD")
    private String password;

    @Column(name = "CL_USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
