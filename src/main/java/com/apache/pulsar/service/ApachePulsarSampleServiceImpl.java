package com.apache.pulsar.service;

import org.apache.pulsar.client.api.*;

/**
 * @author UmutBayram
 */
public class ApachePulsarSampleServiceImpl implements ApachePulsarSampleService {

    private PulsarClient pulsarClient;

    public ApachePulsarSampleServiceImpl(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
    }

    @Override
    public void producer() throws PulsarClientException {
        Producer<String> stringProducer = pulsarClient.newProducer(Schema.STRING)
                .topic("my-topic")
                .create();

        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    try {
                        String msg = "Message " + index;
                        stringProducer.send(msg);
                        System.out.println("Message sent : " + msg);
                        index++;
                        Thread.sleep(1000);
                    } catch (PulsarClientException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        producerThread.start();
    }

    @Override
    public void consumer() throws PulsarClientException {
        MessageListener messageListener = (consumer, msg) -> {
            try {
                System.out.println("Message received: " + new String(msg.getData()));
                consumer.acknowledge(msg);
            } catch (Exception e) {
                consumer.negativeAcknowledge(msg);
            }
        };

        Consumer consumer = pulsarClient.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-subscription")
                .messageListener(messageListener)
                .subscribe();
    }
}
