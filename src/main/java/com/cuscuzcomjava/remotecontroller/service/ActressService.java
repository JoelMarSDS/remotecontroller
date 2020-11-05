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

    public Actress saveActress(Actress actress) throws Exception {

        User user = userRepository.findByLogin(actress.getUser().getLogin());
        if (user != null){
            throw new ConflictException(PropertiesSourceMessange.getMessageSource("user.already.exists"));
        }

        actress.getUser().setTypeUserEnumeration(TypeUserEnumeration.USER_COMUM);
        User userComum = userRepository.save(actress.getUser());

        actress.setUser(userComum);
        Actress saveActress = actressRepository.save(actress);

        return saveActress;
    }

    public List<Actress> getListActress() throws Exception {
        List<Actress> actresses = actressRepository.findAll();
        if (actresses.isEmpty()){
            throw new EntityNotFundException(PropertiesSourceMessange.getMessageSource("list.is.empty"));
        }
        return actresses;
    }

    public Actress updateActress (Actress actress, Long id) throws Exception {
        Actress actressExistent = actressRepository.findById(id).orElse(null);

        if (actressExistent == null){
            throw new ActivationException(PropertiesSourceMessange.getMessageSource("actress.already.not.exists"));
        }

        actressExistent = actress;
        actressExistent.setId(id);
        return actressRepository.save(actressExistent);
    }

    public Actress getById(Long id) throws Exception {
        Actress actress = actressRepository.findById(id).orElse(null);
        if (actress == null){
            throw new ActivationException(PropertiesSourceMessange.getMessageSource("actress.already.not.exists"));
        }
        return actress;
    }

    public Actress deleteActress(Long id) throws Exception {

        Actress actress = actressRepository.findById(id).orElse(null);

        if (actress == null){
            throw new EntityNotFundException(PropertiesSourceMessange.getMessageSource("user.already.not.exists"));
        }

        actressRepository.delete(actress);

        return actress;
    }

}
