package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFoundException;
import com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties.PropertiesSourceMessange;
import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.entity.User;
import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.repository.UserRepository;
import java.time.LocalDate;
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

    @Autowired
    private ReserveService reserveService;

    public Actress save(Actress actress) throws ConflictException {
        User user = userRepository.findByLogin(actress.getUser().getLogin());
        if (user != null){
            throw new ConflictException(PropertiesSourceMessange.getMessageSource("actress.already.exists"));
        }

        actress.getUser().setTypeUserEnumeration(TypeUserEnumeration.COMMON_USER);
        User userComum = userRepository.save(actress.getUser());

        actress.setUser(userComum);
        return actressRepository.save(actress);

    }

    public List<Actress> getAll() {
        return actressRepository.findAll();
    }

    public Actress update(Actress actress, Long id) throws ActivationException {
        Actress existentActress = actressRepository.findById(id).orElse(null);

        if (existentActress == null) {
            throw new ActivationException(PropertiesSourceMessange.getMessageSource("actress.does.not.exists"));
        }

        User existentUser = userRepository.findById(existentActress.getUser().getId()).orElse(null);

        existentUser = actress.getUser();
        existentUser.setId(existentActress.getUser().getId());
        existentUser.setTypeUserEnumeration(existentActress.getUser().getTypeUserEnumeration());
        userRepository.save(existentUser);

        existentActress = actress;
        existentActress.setId(id);

        return actressRepository.save(existentActress);
    }

    public Actress getById(Long id) throws ActivationException {
        return actressRepository.findById(id)
            .orElseThrow(() -> new ActivationException(PropertiesSourceMessange.getMessageSource(
                "actress.does.not.exists")));
    }

    public Actress delete(Long id) throws EntityNotFoundException {
        Actress actress = actressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("actress.does.not.exists")));

        actressRepository.deleteById(id);
        userRepository.deleteById(actress.getUser().getId());
        return actress;
    }

    public List<Actress> getByStatus(boolean actressStatus) {
        return actressRepository.findActressByStatus(actressStatus);
    }

    public Map<Integer, Set<String>> getMostRelevant() {
        List<Actress> actressesList = this.getAll();

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

    public Map<Integer, Set<String>> getLessRelevant() {
        List<Actress> actressesList = this.getAll();

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

    public Map<Double, Set<String>> getMostExpensive() {
        List<Actress> actressesList = this.getAll();

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

    public Map<Double, Set<String>> getLessExpensive() {
        List<Actress> actressesList = this.getAll();

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

    public boolean isAvailableAtDate(Long id, LocalDate date) {
        List<Reserve> reserves = null;
        try {
            reserves = reserveService.getReserveActress(id);
        } catch (ActivationException e) {
            System.out.println("Atriz não encontrada, por favor informe um usuário existente.");
            return false;
        }

        for (Reserve reserve : reserves) {
            if (reserve.getReserveDate().isEqual(date)) {
                return false;
            }
        }

        return true;
    }
}
