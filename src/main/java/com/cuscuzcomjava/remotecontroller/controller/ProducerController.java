package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @PostMapping("/create")
    public ResponseEntity<Producer> createProducer(@RequestBody Producer producer) throws Exception{
        return ResponseEntity.ok(producerService.createProducer(producer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> getProducer(@PathVariable Long id){
        return ResponseEntity.ok(producerService.getProducer(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Producer> updateProducer(@PathVariable Long id, @RequestBody Producer producer){
        return ResponseEntity.ok(producerService.updateProducer(id, producer));
    }

    @DeleteMapping("/{id}/delete")
    public void deleteProducer(@PathVariable Long id) throws Exception {
        producerService.deleteProducer(id);
    }
}
