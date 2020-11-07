package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFoundException;
import com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties.PropertiesSourceMessange;
import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.User;
import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.activation.ActivationException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ActressService {

    @Autowired
    private ActressRepository actressRepository;

    @Autowired
    private UserRepository userRepository;

    public Actress saveActress(Actress actress) throws ConflictException {
        User user = userRepository.findByLogin(actress.getUser().getLogin());
        if (user != null){
            throw new ConflictException(PropertiesSourceMessange.getMessageSource("actress.already.exists"));
        }

        actress.getUser().setTypeUserEnumeration(TypeUserEnumeration.COMMON_USER);
        User userComum = userRepository.save(actress.getUser());

        actress.setUser(userComum);
        return actressRepository.save(actress);

    }

    public List<Actress> getListActress() {
        return actressRepository.findAll();
    }

    public Actress updateActress (Actress actress, Long id) throws ActivationException {
        Actress existentActress = actressRepository.findById(id).orElse(null);

        if (existentActress == null) {
            throw new ActivationException(PropertiesSourceMessange.getMessageSource("actress.does.not.exists"));
        }

        existentActress = actress;
        existentActress.setId(id);
        return actressRepository.save(existentActress);
    }

    public Actress getById(Long id) throws ActivationException {
        return actressRepository.findById(id)
            .orElseThrow(() -> new ActivationException(PropertiesSourceMessange.getMessageSource(
                "actress.does.not.exists")));
    }

    public Actress deleteActress(Long id) throws EntityNotFoundException {
        Actress actress = actressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("actress.does.not.exists")));

        actressRepository.deleteById(id);
        return actress;
    }

    public List<Actress> getActressByStatus(boolean actressStatus) {
        return actressRepository.findActressByStatus(actressStatus);
    }

    public Map<Integer, Set<String>> getMostRelevantActresses() {
        List<Actress> actressesList = this.getListActress();

        if (actressesList.isEmpty()){
            return null;
        }

        // agrupando atrizes por relevância
        Map<Integer, Set<String>> actressesMap =
                actressesList.stream().collect(
                        Collectors.groupingBy(Actress::getRelevance,
                                Collectors.mapping(Actress::getName, Collectors.toSet())
                        )
                );

        Map<Integer, Set<String>> actressesOrdered = new LinkedHashMap<>();

        //colocando atrizes agrupadas de forma decrescente por relevância
        actressesMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Set<String>>comparingByKey()
                        .reversed()).forEachOrdered(e -> actressesOrdered.put(e.getKey(), e.getValue()));

        return actressesOrdered;
    }

    public Map<Integer, Set<String>> getLessRelevantActresses() {
        List<Actress> actressesList = this.getListActress();

        if (actressesList.isEmpty()){
            return null;
        }

        // agrupando atrizes por relevância
        Map<Integer, Set<String>> actressesMap =
                actressesList.stream().collect(
                        Collectors.groupingBy(Actress::getRelevance,
                                Collectors.mapping(Actress::getName, Collectors.toSet())
                        )
                );

        Map<Integer, Set<String>> actressesOrdered = new LinkedHashMap<>();

        //colocando atrizes agrupadas de forma crescente por relevância
        actressesMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Set<String>>comparingByKey()).forEachOrdered(e -> actressesOrdered.put(e.getKey(), e.getValue()));

        return actressesOrdered;
    }

    public Map<Double, Set<String>> getMostExpensiveActresses() {
        List<Actress> actressesList = this.getListActress();

        if (actressesList.isEmpty()){
            return null;
        }

        // agrupando atrizes por preço
        Map<Double, Set<String>> actressesMap =
                actressesList.stream().collect(
                        Collectors.groupingBy(Actress::getPrice,
                                Collectors.mapping(Actress::getName, Collectors.toSet())
                        )
                );

        Map<Double, Set<String>> actressesOrdered = new LinkedHashMap<>();

        //colocando atrizes agrupadas de forma decrescente por preço
        actressesMap.entrySet().stream()
                .sorted(Map.Entry.<Double, Set<String>>comparingByKey()
                        .reversed()).forEachOrdered(e -> actressesOrdered.put(e.getKey(), e.getValue()));

        return actressesOrdered;
    }

    public Map<Double, Set<String>> getLessExpensiveActresses() {
        List<Actress> actressesList = this.getListActress();

        if (actressesList.isEmpty()){
            return null;
        }

        // agrupando atrizes por preço
        Map<Double, Set<String>> actressesMap =
                actressesList.stream().collect(
                        Collectors.groupingBy(Actress::getPrice,
                                Collectors.mapping(Actress::getName, Collectors.toSet())
                        )
                );

        Map<Double, Set<String>> actressesOrdered = new LinkedHashMap<>();

        //colocando atrizes agrupadas de forma crescente por preço
        actressesMap.entrySet().stream()
                .sorted(Map.Entry.<Double, Set<String>>comparingByKey()).forEachOrdered(e -> actressesOrdered.put(e.getKey(), e.getValue()));

        return actressesOrdered;
    }
}
