package com.affiliatedLink.alCore.controller;

import com.affiliatedLink.alCore.dto.ConsumerRequest;
import com.affiliatedLink.alCore.entity.Consumer;
import com.affiliatedLink.alCore.exception.ConsumerNotFoundException;
import com.affiliatedLink.alCore.service.ConsumerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/consumers")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping
    public ResponseEntity<List<Consumer>> getConsumer(){
        return ResponseEntity.ok(consumerService.getConsumers());
    }

    @PostMapping
    public ResponseEntity<Consumer> registerConsumer(@RequestBody @Valid ConsumerRequest consumerRequest) {
        Consumer consumer = consumerService.registerConsumer(consumerRequest);
        return new ResponseEntity<>(consumer, HttpStatus.CREATED);
    }

    @GetMapping("/{consumerId}")
    public ResponseEntity<Consumer> getConsumerById(@PathVariable UUID consumerId) throws ConsumerNotFoundException {
        return ResponseEntity.ok(consumerService.getConsumerById(consumerId));
    }
}
