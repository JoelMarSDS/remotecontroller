package com.cuscuzcomjava.remotecontroller.service;

import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.repository.ActressRepository;
import com.cuscuzcomjava.remotecontroller.repository.ReserveRepository;
import com.cuscuzcomjava.remotecontroller.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
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
    if (producer == null) {
      return null;
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
