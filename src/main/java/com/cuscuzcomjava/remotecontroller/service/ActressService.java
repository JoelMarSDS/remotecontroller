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

    Actress globalActress;

    public Actress createActress(Actress actress) throws Exception{
        globalActress = repository.findByLogin(actress.getLogin());
        if (globalActress != null){
            throw new Exception("Login already exists");
        }
        return repository.save(actress);
    }

    public List<Reserve> searchReserves(Long id){
        Actress copyActress = repository.getOne(id);
        return copyActress.getReserves();
    }

    public Optional<Actress> getActress(Long id){
        return repository.findById(id);
    }

    public List<Actress> getAllActress(){
        return repository.findAll();
    }
}
