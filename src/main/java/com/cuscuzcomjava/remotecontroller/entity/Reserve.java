package com.cuscuzcomjava.remotecontroller.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserve {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    @NotNull
    private LocalDate reserveDate;

    @ManyToOne
    @JoinColumn(name = "actress_id")
    private Actress actress;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public LocalDate getDateReserved() {
        return reserveDate;
    }

    public void setDateReserved(LocalDate reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Actress getActress() {
        return actress;
    }

    public Producer getProducer() {
        return producer;
    }
}
