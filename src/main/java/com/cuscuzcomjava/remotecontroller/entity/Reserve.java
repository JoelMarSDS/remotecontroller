package com.cuscuzcomjava.remotecontroller.entity;

import jdk.jfr.Enabled;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Reserve {

    @Id
    @GeneratedValue
    @Column(name = "CL_ID")
    private Long id;

    @Column(name = "CL_DATE_RESERVED")
    private Instant dateReserved;

    @Column(name = "CL_ACTRESS_ID")
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

    public Actress getActress() {
        return actress;
    }

    public void setActress(Actress actress) {
        this.actress = actress;
    }
}
