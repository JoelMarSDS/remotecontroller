package com.cuscuzcomjava.remotecontroller.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Producer extends User{

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private List<Reserve> reserves;

    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }
}
