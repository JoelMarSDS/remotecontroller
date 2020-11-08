package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFoundException;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

  public List<Reserve> getReserveActress(Long actressId) throws ActivationException {
    Actress actress = actressRepository.findById(actressId)
        .orElseThrow(() -> new ActivationException(PropertiesSourceMessange.getMessageSource(
            "actress.does.not.exists")));

    return reserveRepository.findByActress(actress);
  }

  public List<Reserve> getReserveProducer(Long id) throws EntityNotFoundException {
    Producer producer = producerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

    return reserveRepository.findByProducer(producer);
  }

  public Integer getCountReserveProducer(Long id) throws EntityNotFoundException {
    Producer producer = producerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

    return reserveRepository.findByProducer(producer).size();
  }

  public Map<LocalDate, Long> getMoreReservedProducerDates(Long producerId) throws
      EntityNotFoundException {
    List<LocalDate> dateList = new ArrayList<>();
    Producer producer = producerRepository.findById(producerId)
        .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource(
            "producer.does.not.exists")));

    for (Reserve auxReserve: reserveRepository.findByProducer(producer)) {
      dateList.add(auxReserve.getReserveDate());
    }

    //contando e agrupando datas
    Map<LocalDate, Long> dates =
            dateList.stream().collect(
                    Collectors.groupingBy(
                            Function.identity(), Collectors.counting()
                    )
            );

    Map<LocalDate, Long> datesOrdered = new LinkedHashMap<>();

    //encontrando datas mais reservadas e colocando em ordem decrescente
    dates.entrySet().stream()
            .sorted(Map.Entry.<LocalDate, Long>comparingByValue()
                    .reversed()).forEachOrdered(e -> datesOrdered.put(e.getKey(), e.getValue()));

    return datesOrdered;
  }

  public Map<String, Long> getMoreReservedActresses(Long producerId) throws
      EntityNotFoundException {
    List<String> actressList = new ArrayList<>();
    Producer producer = producerRepository.findById(producerId)
        .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("producer.does.not.exists")));

    for (Reserve auxReserve: reserveRepository.findByProducer(producer)) {
      actressList.add(auxReserve.getActress().getUser().getLogin());
    }

    //contando e agrupando atrizes
    Map<String, Long> dates =
            actressList.stream().collect(
                    Collectors.groupingBy(
                            Function.identity(), Collectors.counting()
                    )
            );

    Map<String, Long> actressOrdered = new LinkedHashMap<>();

    //encontrando atrizes mais reservadas e colocando em ordem decrescente
    dates.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue()
                    .reversed()).forEachOrdered(e -> actressOrdered.put(e.getKey(), e.getValue()));

    return actressOrdered;
  }

  public List<Reserve> updateReserve(Long oldId, Reserve newReserve) throws
      EntityNotFoundException {
    Reserve reserve = reserveRepository.findById(oldId)
        .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource(
            "reserve.does.not.exists")));

    newReserve.setId(oldId);
    reserveRepository.save(newReserve);
    return getReserveProducer(newReserve.getProducer().getId());
  }

  public List<Reserve> deleteReserve(Long reserveId) throws EntityNotFoundException {
    Reserve reserve = reserveRepository.findById(reserveId)
        .orElseThrow(() -> new EntityNotFoundException(PropertiesSourceMessange.getMessageSource("reserve.does.not.exists")));

    reserveRepository.deleteById(reserveId);
    return getReserveProducer(reserve.getProducer().getId());
  }

}
