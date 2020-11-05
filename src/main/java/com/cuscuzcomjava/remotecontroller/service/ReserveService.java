package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFundException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ProducerException;
import com.cuscuzcomjava.remotecontroller.configuration.util.messageproperties.PropertiesSourceMessange;
import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.repository.ProducerRepository;
import com.cuscuzcomjava.remotecontroller.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.activation.ActivationException;
import java.util.List;

@Component
public class ReserveService {

  @Autowired
  private ReserveRepository reserveRepository;

  @Autowired
  private ProducerRepository producerRepository;

  @Autowired
  private ActressRepository actressRepository;

  public Reserve saveReserve(Reserve reserve, Long actressId) throws Exception {
    Actress actress = actressRepository.findById(actressId).orElse(null);
    if (actress == null){
      throw new ConflictException(PropertiesSourceMessange.getMessageSource(""));
    }
    reserve.setActress(actress);
    Reserve reserveSave = reserveRepository.save(reserve);
    return reserveSave;
  }

  public List<Reserve> getListReserve() throws Exception {
    List<Reserve> reserves = reserveRepository.findAll();
    if (reserves.isEmpty()){
      throw new EntityNotFundException(PropertiesSourceMessange.getMessageSource("list.is.empty"));
    }
    return reserves;
  }

  public List<Reserve> getReserveActress(Long actressId) throws Exception {
    Actress actress = actressRepository.findById(actressId).orElse(null);
    if (actress == null){
      throw new ActivationException(PropertiesSourceMessange.getMessageSource("actress.already.not.exists"));
    }
    List<Reserve> reserves = reserveRepository.findByActress(actress);
    return reserves;
  }

  public List<Reserve> getReserveProducer(Long id) throws Exception {
    Producer producer = producerRepository.findById(id).orElse(null);
    if (producer == null){
      throw new ProducerException(PropertiesSourceMessange.getMessageSource(""));
    }
    List<Reserve> reserves = reserveRepository.findByProducer(producer);
    return reserves;
  }

  public Integer getCountReserveProducer(Long id) throws Exception {
    Producer producer = producerRepository.findById(id).orElse(null);
    if (producer == null){
      throw new EntityNotFundException(PropertiesSourceMessange.getMessageSource(""));
    }
    Integer reserves = reserveRepository.findByProducer(producer).size();
    return reserves;
  }
}
