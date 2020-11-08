package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.ConflictException;
import com.cuscuzcomjava.remotecontroller.configuration.util.exceptions.customexception.EntityNotFoundException;
import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Producer;
import com.cuscuzcomjava.remotecontroller.service.ProducerService;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @PostMapping("/create")
    public ResponseEntity<Producer> saveProducer(@RequestBody Producer producer) throws
        ConflictException, Exception {
        if (producer != null){
            Producer saveProducer = producerService.saveProducer(producer);
            URI uri = URI.create(String.format("/producer/create/%d",saveProducer.getId()));
            return ResponseEntity.created(uri).body(saveProducer);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{byId}")
    public ResponseEntity<Producer> getById(@PathVariable Long byId) throws EntityNotFoundException {
        if (byId != null){
            Producer producer = producerService.getProducerById(byId);
            return ResponseEntity.ok(producer);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{updateProducerId}")
    public ResponseEntity<Producer> updateProducer(@RequestBody Producer producer, @PathVariable Long updateProducerId) throws EntityNotFoundException, Exception {
        if (producer != null && updateProducerId != null) {
            Producer producerUpdate = producerService.updateProducer(producer, updateProducerId);
            return ResponseEntity.ok(producerUpdate);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Producer> deleteProducer(@PathParam("id") Long id) throws
        EntityNotFoundException, Exception {
        if (id != null) {
            Producer producer = producerService.deleteProducer(id);
            return ResponseEntity.ok(producer);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getCast")
    public ResponseEntity<List<Actress>> getCast(
        @RequestParam int quantity,
        @RequestParam String genre,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam double budget)
    {
        return ResponseEntity.ok(producerService.getCast(quantity, genre, date, budget));
    }

}
