package com.raf.sk_user_service;

import org.apache.activemq.broker.BrokerService;

public class MessageBroker {
    public static void main(String[] args) throws Exception{
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.start();

        System.out.println("ActiveMQ broker started. Press Enter to stop.");
        System.in.read(); // Čekanje na unos s tastature
        broker.stop();
    }
}
