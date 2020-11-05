package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @PostMapping("/create")
    public ResponseEntity<Producer> saveProducer(@RequestBody Producer producer) throws Exception {
        if (producer != null){
            Producer saveProducer = producerService.saveProducer(producer);
            return ResponseEntity.ok(saveProducer);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateProducer/{updateProducerId}")
    public ResponseEntity<Producer> updateProducer(@RequestBody Producer producer, @PathVariable Long updateProducerId) throws Exception {
        if (producer != null && updateProducerId != null) {
            Producer producerUpdate = producerService.updateProducer(producer, updateProducerId);
            return ResponseEntity.ok(producerUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("deleteActress/{deleteActressId}")
    public ResponseEntity<Producer> deleteActress(@PathVariable Long deleteActressId) throws Exception {
        if (deleteActressId != null) {
            Producer producer = producerService.deleteProducer(deleteActressId);
            return ResponseEntity.ok(producer);
        }
        return ResponseEntity.notFound().build();
    }

}
