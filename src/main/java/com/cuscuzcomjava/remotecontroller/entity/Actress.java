package com.cuscuzcomjava.remotecontroller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Actress {

    @Id
    @GeneratedValue
    @Column(name = "CL_ID")
    private Long id;

    @Column(name = "CL_GENDER")
    private String gender;

    @Column(name = "CL_PRICE")
    private Double price;

    @Column(name = "CL_STARS")
    private Integer stars;

    @Column(name = "CL_GENRE")
    private String genre;

    @Column(name = "CL_STATUS")
    private Boolean status;

    @Column(name = "CL_RESERVES")
    private Reserve reserves;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
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

    public Reserve getReserves() {
        return reserves;
    }

    public void setReserves(Reserve reserves) {
        this.reserves = reserves;
    }
}
