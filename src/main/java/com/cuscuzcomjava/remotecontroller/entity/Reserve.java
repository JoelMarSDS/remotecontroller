package com.cuscuzcomjava.remotecontroller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserve {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotNull
    private LocalDate reserveDate;

    @ManyToOne
    @JoinColumn(name = "actress_id")
    private Actress actress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateReserved() {
        return reserveDate;
    }

    public void setDateReserved(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Actress getActress() {
        return actress;
    }
}
