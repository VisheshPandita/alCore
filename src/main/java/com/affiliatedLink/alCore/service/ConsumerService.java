package com.affiliatedLink.alCore.service;

import com.affiliatedLink.alCore.dto.ConsumerRequest;
import com.affiliatedLink.alCore.entity.Consumer;
import com.affiliatedLink.alCore.exception.ConsumerNotFoundException;
import com.affiliatedLink.alCore.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    public List<Consumer> getConsumers() {
        return consumerRepository.findAll();
    }

    public Consumer registerConsumer(ConsumerRequest consumerRequest) {
        Consumer consumer = new Consumer(
                consumerRequest.getConsumerFirstName(),
                consumerRequest.getConsumerLastName(),
                consumerRequest.getConsumerEmail()
        );

        return consumerRepository.save(consumer);
    }

    public Consumer getConsumerById(UUID consumerId) throws ConsumerNotFoundException {
        Optional<Consumer> consumer = consumerRepository.findById(consumerId);
        if(consumer.isPresent()) return consumer.get();
        else throw new ConsumerNotFoundException("User not found with id " + consumerId.toString());
    }
}
