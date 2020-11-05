package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFundException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ProducerException;
import com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties.PropertiesSourceMessange;
import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.entity.User;
import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.repository.ProducerRepository;
import com.cuscuzcomjava.remotecontroller.repository.ReserveRepository;
import com.cuscuzcomjava.remotecontroller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private ActressRepository actressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    public Producer saveProducer(Producer producer) throws Exception {
        User user = userRepository.findByLogin(producer.getUser().getLogin());
        if (user == null){
            throw new ConflictException(PropertiesSourceMessange.getMessageSource(""));
        }
        producer.getUser().setTypeUserEnumeration(TypeUserEnumeration.ADMIN);
        Producer saveProducer = producerRepository.save(producer);
        return saveProducer;
    }

    public Producer updateProducer (Producer producer, Long id) throws Exception {
        Producer producerExistent = producerRepository.findById(id).orElse(null);

        if (producerExistent == null){
            throw new ProducerException(PropertiesSourceMessange.getMessageSource(""));
        }

        producerExistent = producer;
        producerExistent.setId(id);
        return producerRepository.save(producerExistent);
    }

    public Producer deleteProducer(Long id) throws Exception {

        Producer producer = producerRepository.findById(id).orElse(null);

        if (producer == null){
            throw new EntityNotFundException(PropertiesSourceMessange.getMessageSource(""));
        }

        producerRepository.delete(producer);

        return producer;
    }

}
