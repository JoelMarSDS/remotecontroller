package com.cuscuzcomjava.remotecontroller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Reserve {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotNull
    private Instant dateReserved;

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

    public Instant getDateReserved() {
        return dateReserved;
    }

    public void setDateReserved(Instant dateReserved) {
        this.dateReserved = dateReserved;
    }

}
