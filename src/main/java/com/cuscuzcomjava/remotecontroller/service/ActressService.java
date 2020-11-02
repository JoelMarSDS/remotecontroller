package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.util.ElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActressService {

    @Autowired
    ActressRepository repository;

    public Actress createActress(Actress actress) throws Exception{
        Actress auxActress = repository.findByLogin(actress.getLogin());
        if (auxActress != null){
            throw new Exception("Login already exists");
        }
        return repository.save(actress);
    }


    public Actress getActress(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Actress> getActressByStatus(){
        List<Actress> activeActresses = new ArrayList<>();
        List<Actress> allActress = repository.findAll();
        if (allActress.isEmpty()){
            return null;
        }
        for (Actress actress: allActress
             ) {
            if(actress.getStatus()){
                activeActresses.add(actress);
            }
        }
        return activeActresses;
    }

    public List<Actress> getAllActresses(){
        return repository.findAll();
    }

    public Actress updateActress(Long id, Actress actress) {
        Actress auxActress = repository.findById(id).orElse(null);
        if (auxActress == null) {
            return null;
        }
        actress.setId(auxActress.getId());
        return repository.save(actress);
    }

    public void deleteActress(Long id) {
        repository.deleteById(id);
    }
}
