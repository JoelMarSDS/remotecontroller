package com.cuscuzcomjava.remotecontroller.entity;

import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Entity
public class Actress implements UserDetails{

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private  String name;

    @Column
    private String gender;

    @Column
    private Double price;

    @Column
    private Integer relevance;

    @Column
    private String genre;

    @Column
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "actress")
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getRelevance() {
        return relevance;
    }

    public void setRelevance(Integer relevance) {
        this.relevance = relevance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @JsonIgnore
    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<User> users = new ArrayList<>();
        User localUser = new User();
        localUser.setTypeUserEnumeration(TypeUserEnumeration.COMMON_USER);
        users.add(localUser);
        return users;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthoriti(){
////        List<String> roles = new ArrayList<>();
////        String role = this.user.getAuthority();
////        roles.add(role);
////        return (Collection<? extends GrantedAuthority>) roles;
//    }

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
