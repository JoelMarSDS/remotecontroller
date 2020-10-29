package com.cuscuzcomjava.remotecontroller.entity;

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
    @JoinColumn
    private Actress actress;

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

//    public Actress getActress() {
//        return actress;
//    }
//
//    public void setActress(Actress actress) {
//        this.actress = actress;
//    }
}
