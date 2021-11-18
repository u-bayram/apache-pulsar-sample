package com.apache.pulsar.service;

import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author UmutBayram
 */
public interface ApachePulsarSampleService {

    void producer() throws PulsarClientException;
    void consumer() throws PulsarClientException;
}
