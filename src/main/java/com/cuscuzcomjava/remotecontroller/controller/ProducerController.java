package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createProducer(@RequestBody Producer producer) throws Exception {
        producerService.createProducer(producer);
        return ResponseEntity.ok(Map.of("message", "Producer was created"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> getProducer(@PathVariable Long id) {
        return ResponseEntity.ok(producerService.getProducer(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producer>> getAllProducers() {
        return ResponseEntity.ok(producerService.getAllProducer());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Producer> updateProducer(@PathVariable Long id, @RequestBody Producer producer) {
        return ResponseEntity.ok(producerService.updateProducer(id, producer));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteProducer(@RequestParam Long id){
        producerService.deleteProducer(id);
        return ResponseEntity.ok(Map.of("message", "Producer was deleted"));
    }
}
