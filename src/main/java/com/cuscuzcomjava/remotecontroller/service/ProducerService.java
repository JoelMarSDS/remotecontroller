package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFoundException;
import com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties.PropertiesSourceMessange;
import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.entity.User;
import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.repository.ProducerRepository;
import com.cuscuzcomjava.remotecontroller.repository.ReserveRepository;
import com.cuscuzcomjava.remotecontroller.repository.UserRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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

    @Autowired
    private ActressService actressService;

    @Autowired
    private ReserveService reserveService;

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

    public Producer getProducerById(Long id) throws EntityNotFoundException {
        return producerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource(
                        "producer.does.not.exists")));
    }

    public Producer updateProducer(Producer producer, Long id) throws EntityNotFoundException {
        Producer existentProducer = producerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

        existentProducer = producer;
        existentProducer.setId(id);
        return producerRepository.save(existentProducer);
    }

    public Producer deleteProducer(Long id) throws EntityNotFoundException {
        Producer producer = producerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

        producerRepository.delete(producer);

        return producer;
    }

    public List<Actress> getCast(int actressesQuantity, String genre, LocalDate date,
        Double budget) {
        int matchQuantity = 0;

        List<Actress> availableActresses = actressService.getAll().stream()
            .filter(actress -> actress.getGenre().contains(genre))
            .filter(actress -> actressService.isAvailableAtDate(actress.getId(), date))
            .collect(Collectors.toList());
        Collections.shuffle(availableActresses);

        List<Actress> matchActresses = new LinkedList<>();

        for (Actress actress : availableActresses) {
            if (budget - actress.getPrice() >= 0) {
                budget -= actress.getPrice();
                matchActresses.add(actress);
                matchQuantity++;
            }

            if (matchQuantity == actressesQuantity || budget == 0) {
                break;
            }
        }

        return matchActresses;
    }
}
