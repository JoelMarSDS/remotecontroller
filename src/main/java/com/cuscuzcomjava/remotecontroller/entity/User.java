package com.cuscuzcomjava.remotecontroller.entity;

import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class User {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private TypeUserEnumeration typeUserEnumeration;

    @OneToOne(mappedBy = "user")
    private Actress actress;

    @OneToOne(mappedBy = "user")
    private Producer process;
    @JsonIgnore
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeUserEnumeration getTypeUserEnumeration() {
        return typeUserEnumeration;
    }

    public void setTypeUserEnumeration(TypeUserEnumeration typeUserEnumeration) {
        this.typeUserEnumeration = typeUserEnumeration;
    }
    @JsonIgnore
    public Actress getActress() {
        return actress;
    }

    public void setActress(Actress actress) {
        this.actress = actress;
    }
    @JsonIgnore
    public Producer getProcess() {
        return process;
    }

    public void setProcess(Producer process) {
        this.process = process;
    }
}
