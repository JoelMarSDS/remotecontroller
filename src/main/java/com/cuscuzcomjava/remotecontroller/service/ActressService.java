package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFundException;
import com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties.PropertiesSourceMessange;
import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.User;
import com.cuscuzcomjava.remotecontroller.entity.enumeration.TypeUserEnumeration;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.activation.ActivationException;
import java.util.List;

@Component
public class ActressService {

    @Autowired
    private ActressRepository actressRepository;

    @Autowired
    private UserRepository userRepository;

    public Actress saveActress(Actress actress) throws ConflictException {
        User user = userRepository.findByLogin(actress.getUser().getLogin());
        if (user != null){
            throw new ConflictException(PropertiesSourceMessange.getMessageSource("user.already.exists"));
        }

        actress.getUser().setTypeUserEnumeration(TypeUserEnumeration.COMMON_USER);
        user = userRepository.save(actress.getUser());

        actress.setUser(user);
        return actressRepository.save(actress);
    }

    public List<Actress> getListActress() {
        return actressRepository.findAll();
    }

    public Actress updateActress (Actress actress, Long id) throws ActivationException {
        Actress existentActress = actressRepository.findById(id)
            .orElseThrow(() -> new ActivationException(PropertiesSourceMessange.getMessageSource(
                "actress.does.not.exists")));

        existentActress = actress;
        existentActress.setId(id);
        return actressRepository.save(existentActress);
    }

    public Actress getById(Long id) throws ActivationException {
        Actress actress = actressRepository.findById(id)
            .orElseThrow(() -> new ActivationException(PropertiesSourceMessange.getMessageSource(
            "actress.does.not.exists")));

        return actress;
    }

    public Actress deleteActress(Long id) throws EntityNotFundException {
        Actress actress = actressRepository.findById(id)
            .orElseThrow(() -> new EntityNotFundException(PropertiesSourceMessange.getMessageSource(
            "user.does.not.exists")));

        actressRepository.delete(actress);

        return actress;
    }
}
