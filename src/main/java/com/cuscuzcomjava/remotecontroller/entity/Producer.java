package com.cuscuzcomjava.remotecontroller.entity;

import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Producer implements UserDetails {


    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private  String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "producer")
    private List<Reserve> reserves;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<User> users = new ArrayList<>();
        User localUser = new User();
        localUser.setTypeUserEnumeration(TypeUserEnumeration.ADMIN);
        users.add(localUser);
        return users;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
