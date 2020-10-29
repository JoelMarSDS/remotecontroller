package com.cuscuzcomjava.remotecontroller.entity;

import javax.persistence.*;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private  String name;

    @Column
    private String login;

    @Column
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
