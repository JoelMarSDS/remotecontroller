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

  public Reserve saveReserve(Reserve reserve, Long actressId) throws ConflictException {
    Actress actress = actressRepository.findById(actressId)
        .orElseThrow(() -> new ConflictException(PropertiesSourceMessange.getMessageSource("actress.does.not.exists")));

    reserve.setActress(actress);

    return reserveRepository.save(reserve);
  }

  public List<Reserve> getListReserve() {
    return reserveRepository.findAll();
  }

  public List<Reserve> getReserveActress(Long actressId) throws Exception {
    Actress actress = actressRepository.findById(actressId)
        .orElseThrow(() -> new ActivationException(PropertiesSourceMessange.getMessageSource(
            "actress.does.not.exists")));

    return reserveRepository.findByActress(actress);
  }

  public List<Reserve> getReserveProducer(Long id) throws ProducerException {
    Producer producer = producerRepository.findById(id)
        .orElseThrow(() -> new ProducerException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

    return reserveRepository.findByProducer(producer);
  }

  public Integer getCountReserveProducer(Long id) throws EntityNotFundException {
    Producer producer = producerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFundException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

    return reserveRepository.findByProducer(producer).size();
  }
}
