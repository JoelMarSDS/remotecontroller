package com.cuscuzcomjava.remotecontroller.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Producer extends User{

    @OneToMany(mappedBy = "producer")
    private List<Reserve> reserves;

    public List<Reserve> getReserves() {
        return reserves;
    }

    public void setReserves(List<Reserve> reserves) {
        this.reserves = reserves;
    }
}
