package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    public Producer createProducer(Producer producer) throws Exception{
        Producer auxProducer = producerRepository.findByLogin(producer.getLogin());
        if (auxProducer != null){
            throw new Exception("Producer login already exists");
        }
        return producerRepository.save(producer);
    }

    public Producer getProducer(Long id){
        return producerRepository.findById(id).orElse(null);
    }

    public Producer updateProducer(Long id, Producer producer){
        Producer auxProducer = producerRepository.findById(id).orElse(null);
        if (auxProducer == null){
            return null;
        }
        producer.setId(auxProducer.getId());
        return producerRepository.save(producer);
    }

    public void deleteProducer(Long id) throws Exception {
        Producer auxProducer = producerRepository.findById(id).orElse(null);
        if (auxProducer == null){
            throw new Exception("Producer not exists");
        }
        producerRepository.delete(auxProducer);

    }



}
