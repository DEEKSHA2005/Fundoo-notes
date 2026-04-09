package com.bridgelabz.fundoonotes.service.impl;

import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final MessageConsumer messageConsumer;

    public MessageProducer(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public void sendMessage(String message) {
        System.out.println("Sending Message: " + message);

        // simulate sending to queue → directly calling consumer
        messageConsumer.receiveMessage(message);
    }
}