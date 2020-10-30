package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ActressService {

    @Autowired
    ActressRepository repository;

    public Actress createActress(Actress actress){
        if (repository.findAll().contains(actress)){
            System.out.println("Actress "+ actress.getName()+" already exists");
        }
        return repository.save(actress);
    }

    public List<Reserve> searchReserves(Long id){
        Actress copyActress = repository.getOne(id);
        return copyActress.getReserves();
    }


}
