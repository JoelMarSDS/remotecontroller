package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

    @Autowired
    ProducerRepository repository;

    public Producer createProducer(Producer producer) throws Exception{
        Producer auxProducer = repository.findByLogin(producer.getLogin());
        if (auxProducer != null){
            throw new Exception("Producer login already exists");
        }
        return repository.save(producer);
    }

    public Producer getProducer(Long id){
        return repository.findById(id).orElse(null);
    }

    public Producer updateProducer(Long id, Producer producer){
        Producer auxProducer = repository.findById(id).orElse(null);
        if (auxProducer == null){
            return null;
        }
        producer.setId(auxProducer.getId());
        return repository.save(producer);
    }

    public void deleteProducer(Long id){
        Producer auxProducer = repository.findById(id).orElse(null);
        if (auxProducer == null){
            return;
        }
        repository.delete(auxProducer);
    }



}
