package com.cuscuzcomjava.remotecontroller.controller;

import com.cuscuzcomjava.remotecontroller.entity.Actress;
import com.cuscuzcomjava.remotecontroller.entity.Reserve;
import com.cuscuzcomjava.remotecontroller.service.ActressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actress")
public class ActressController {

    @Autowired
    private ActressService actressService;

    @PostMapping("/create")
    public ResponseEntity<Actress> saveActress(@RequestBody Actress actress) throws Exception {
        if (actress != null) {
            Actress actressSave = actressService.saveActress(actress);
            URI uri = URI.create(String.format("/actress/create/%d",actressSave.getId()));
            return ResponseEntity.created(uri).body(actressSave);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listActresses")
    public ResponseEntity<List<Actress>> getListActress() throws Exception {
        List<Actress> actresses = actressService.getListActress();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/actressId/{byId}")
    public ResponseEntity<Actress> getById(@PathVariable Long byId) throws Exception {
        if (byId != null){
            Actress actress = actressService.getById(byId);
            return ResponseEntity.ok(actress);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getActressesByStatus")
    public ResponseEntity<List<Actress>> getActressByStatus(@RequestParam ("status") boolean actressStatus) throws Exception{
        List<Actress> actressesByStatus = actressService.getActressByStatus(actressStatus);
        if (!actressesByStatus.isEmpty()){
            return ResponseEntity.ok(actressesByStatus);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getMostRelevantActresses")
    public ResponseEntity<Map<Integer, Set<String>>> getMostRelevantActresses() throws Exception{
        Map<Integer, Set<String>> actresses = actressService.getMostRelevantActresses();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getLessRelevantActresses")
    public ResponseEntity<Map<Integer, Set<String>>> getLessRelevantActresses() throws Exception{
        Map<Integer, Set<String>> actresses = actressService.getLessRelevantActresses();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getMostExpensiveActresses")
    public ResponseEntity<Map<Double, Set<String>>> getMostExpensiveActresses() throws Exception{
        Map<Double, Set<String>> actresses = actressService.getMostExpensiveActresses();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getLessExpensiveActresses")
    public ResponseEntity<Map<Double, Set<String>>> getLessExpensiveActresses() throws Exception{
        Map<Double, Set<String>> actresses = actressService.getLessExpensiveActresses();
        if (!actresses.isEmpty()){
            return ResponseEntity.ok(actresses);
        }
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/updateActress/{updateActressId}")
    public ResponseEntity<Actress> updateActress(@RequestBody Actress actress, @PathVariable Long updateActressId) throws Exception {
        if (actress != null && updateActressId != null){
            Actress actressUpdate = actressService.updateActress(actress, updateActressId);
            return ResponseEntity.ok(actressUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("deleteActress/{deleteActressId}")
    public ResponseEntity<Actress> deleteActress(@PathVariable Long deleteActressId) throws Exception {
        if (deleteActressId != null) {
            Actress actress = actressService.deleteActress(deleteActressId);
            return ResponseEntity.ok(actress);
        }
        return ResponseEntity.notFound().build();
    }

}