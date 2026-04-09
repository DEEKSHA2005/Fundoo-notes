package com.bridgelabz.fundoonotes.service.impl;

import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    public void receiveMessage(String message) {

        System.out.println("Received Message: " + message);

        // Simulate Email Sending
        System.out.println("Sending Email Notification...");
        System.out.println("Email Content: " + message);

        // Simulate App Notification
        System.out.println("Notification sent to user successfully!");
    }
}