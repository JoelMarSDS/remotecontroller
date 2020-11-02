package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.service.ReserveService;
import java.util.List;
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
  ReserveService service;

  @PostMapping("/create")
  public ResponseEntity<List<Reserve>> createReserve(@RequestBody Reserve reserve) {
    return ResponseEntity.ok(service.createReserve(reserve));
  }

  @GetMapping("/{id}/actress")
  public ResponseEntity<List<Reserve>> getAllActressReserves(@PathVariable Long id) {
    return ResponseEntity.ok(service.getAllActressReserves(id));
  }

  @GetMapping("/{id}/producer")
  public ResponseEntity<List<Reserve>> getAllProducerReserves(@PathVariable Long id) {
    return ResponseEntity.ok(service.getAllProducerReserves(id));
  }

  @PutMapping("/{id}/update")
  public ResponseEntity<List<Reserve>> updateReserve(@PathVariable Long id,
      @RequestBody Reserve reserve) {
    return ResponseEntity.ok(service.updateReserve(id, reserve));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<List<Reserve>> deleteReserve(@RequestParam Long id) {
    return ResponseEntity.ok(service.deleteReserve(id));
  }
}
