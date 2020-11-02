package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.repository.ReserveRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReserveService {

  @Autowired
  ReserveRepository repository;

  @Autowired
  ActressService actressService;

  @Autowired
  ProducerService producerService;

  public List<Reserve> createReserve(Reserve reserve) {
    Producer producer = producerService.getProducer(reserve.getProducer().getId());
    Actress actress = actressService.getActress(reserve.getActress().getId());
    if (producer == null) {
      return null;
    }

    if(actress == null) {//verificando existência da atriz
      return null;
    }

    if(!actress.getStatus()){ //impede reservar estado inativo
      return null;
    }

    for (Reserve auxReserve: repository.findAllByProducerId(producer.getId())) { //impede a criação de reservas repetidas
      if (auxReserve.getDateReserved().equals(reserve.getDateReserved())
              && auxReserve.getActress().getId().equals(reserve.getActress().getId())){
        return null;
      }
    }

//    List checkDate =(List) repository.findAllByActressId(actress.getId()).stream()
//        .filter(d -> d.getDateReserved() == reserve.getDateReserved());
//    if (!checkDate.isEmpty()) {
//      return getAllReserves(actress.getId());
//    }

    repository.save(reserve);
    return repository.findAllByProducerId(producer.getId());
  }

  public List<Reserve> getAllActressReserves(Long id) {
    return repository.findAllByActressId(id);
  }

  public List<Reserve> getAllProducerReserves(Long id) {
    return repository.findAllByProducerId(id);
  }

  public Integer getProducerReservesNumber(Long id){ //countReserves
    return repository.findAll().size();
  }

  public Map<LocalDate, Long> getDatesMoreReserved(Long id){
    List<LocalDate> dateList = new ArrayList<>();
    for (Reserve auxReserve: repository.findAllByProducerId(id)) {
      dateList.add(auxReserve.getDateReserved());
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

  public List<Reserve> updateReserve(Long oldId, Reserve newReserve) {
    Reserve reserve = repository.findById(oldId).orElse(null);
    if (reserve == null){
      return null;
    }

    newReserve.setId(oldId);
    repository.save(newReserve);
    return getAllProducerReserves(newReserve.getProducer().getId());
  }

  public List<Reserve> deleteReserve(Long id) {
    Reserve reserve = repository.findById(id).orElse(null);
    if (reserve == null) {
      return null;
    }

    repository.deleteById(id);
    return getAllProducerReserves(reserve.getProducer().getId());
  }
}
