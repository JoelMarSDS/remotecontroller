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

    public Producer saveProducer(Producer producer) throws ConflictException {
        User user = userRepository.findByLogin(producer.getUser().getLogin());

        if (user != null){
            throw new ConflictException(PropertiesSourceMessange.getMessageSource("producer.already.exists"));
        }

        producer.getUser().setTypeUserEnumeration(TypeUserEnumeration.ADMIN);

        User userAdmin = userRepository.save(producer.getUser());
        producer.setUser(userAdmin);

        return producerRepository.save(producer);
    }

    public Producer updateProducer(Producer producer, Long id) throws ProducerException {
        Producer existentProducer = producerRepository.findById(id).orElse(null);

        if (existentProducer == null) {
            throw new ProducerException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists"));
        }

        existentProducer = producer;
        existentProducer.setId(id);
        return producerRepository.save(existentProducer);
    }

    public Producer deleteProducer(Long id) throws EntityNotFundException {
        Producer producer = producerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFundException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

        producerRepository.delete(producer);

        return producer;
    }

}
