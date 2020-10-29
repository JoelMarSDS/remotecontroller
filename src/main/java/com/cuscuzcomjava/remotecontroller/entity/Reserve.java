package com.cuscuzcomjava.remotecontroller.entity;

<<<<<<< HEAD
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
=======
import com.sun.istack.NotNull;

import javax.persistence.*;
>>>>>>> creatEntities
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
