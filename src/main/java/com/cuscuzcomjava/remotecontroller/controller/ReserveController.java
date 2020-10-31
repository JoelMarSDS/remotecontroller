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

@Controller
@RequestMapping("/actress")
public class ReserveController {

  @Autowired
  ReserveService service;

  @PostMapping("/reserve/create")
  public ResponseEntity<List<Reserve>> createResponse(@RequestBody Reserve reserve) {
    return ResponseEntity.ok(service.createReserve(reserve));
  }

  @GetMapping("/{id}/reserves")
  public ResponseEntity<List<Reserve>> getAllReserves(@PathVariable Long id) {
    return ResponseEntity.ok(service.getAllReserves(id));
  }

  @PutMapping("/{id}/reserve/update")
  public ResponseEntity<List<Reserve>> updateReserve(@PathVariable Long id,
      @RequestBody Reserve reserve) {
    return ResponseEntity.ok(service.updateReserve(id, reserve));
  }

  @DeleteMapping("/reserve/delete")
  public ResponseEntity<List<Reserve>> deleteReserve(@RequestParam Long id) {
    return ResponseEntity.ok(service.deleteReserve(id));
  }
}
