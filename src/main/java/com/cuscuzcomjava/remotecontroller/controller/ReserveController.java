package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.service.ReserveService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

  @Autowired
  private ReserveService reserveService;

  @PostMapping("/saveReserve/{saveReserveId}")
  public ResponseEntity<Reserve> saveReserve(@RequestBody Reserve reserve, @PathVariable Long saveReserveId) throws Exception {
    if (reserve != null) {
      Reserve saveReserve = reserveService.saveReserve(reserve, saveReserveId);
      URI uri = URI.create(String.format("/reserve/create/%d",reserve.getId()));
      return ResponseEntity.created(uri).body(reserve);
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/listReserve")
  public ResponseEntity<List<Reserve>> getListReserve() throws Exception {
    List<Reserve> reserves = reserveService.getListReserve();
    if (!reserves.isEmpty()){
      return ResponseEntity.ok(reserves);
    }
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/listReserveActress/{reserveActressId}")
  public ResponseEntity<List<Reserve>> getReserveActress(@PathVariable Long reserveActressId) throws Exception {
    if (reserveActressId != null){
      List<Reserve> reserves = reserveService.getReserveActress(reserveActressId);
      return ResponseEntity.ok(reserves);
    }
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/listReserveProducer/{reserveProducerId}")
  public ResponseEntity<List<Reserve>> getReserveProducer(@PathVariable Long reserveProducerId) throws Exception {
    if (reserveProducerId != null) {
      List<Reserve> reserves = reserveService.getReserveProducer(reserveProducerId);
      return ResponseEntity.ok(reserves);
    }
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/countReserveProducer/{countReserveProducerId}")
  public ResponseEntity<Integer> getCountReserveProducer(@PathVariable Long countReserveProducerId) throws Exception {
    if (countReserveProducerId != null){
      Integer countReserve = reserveService.getCountReserveProducer(countReserveProducerId);
      return ResponseEntity.ok(countReserve);
    }
    return ResponseEntity.noContent().build();
  }

  @GetMapping("getMoreReservedProducerDates/{producerId}")
  public ResponseEntity<Map<LocalDate, Long>> getMoreReservedProducerDates(@PathVariable Long producerId) throws Exception{
    if (producerId != null){
      Map<LocalDate, Long> moreReservedProducerDates = reserveService.getMoreReservedProducerDates(producerId);
      return ResponseEntity.ok(moreReservedProducerDates);
    }
    return ResponseEntity.noContent().build();
  }

  @GetMapping("getMoreReservedActresses/{producerId}")
  public ResponseEntity<Map<String, Long>> getMoreReservedActresses(@PathVariable Long producerId) throws Exception{
    if (producerId != null){
      Map<String, Long> moreReservedActresses = reserveService.getMoreReservedActresses(producerId);
      return ResponseEntity.ok(moreReservedActresses);
    }
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/updateReserve/{reserveId}")
  public ResponseEntity<List<Reserve>> updateReserve(@PathVariable Long reserveId, @RequestBody Reserve reserve) throws Exception{
    if (reserveId != null){
      List<Reserve> reserves = reserveService.updateReserve(reserveId, reserve);
      return ResponseEntity.ok(reserves);
    }
    return ResponseEntity.noContent().build();
  }

}
