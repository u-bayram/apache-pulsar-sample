package com.apache.pulsar;

import com.apache.pulsar.service.ApachePulsarSampleService;
import com.apache.pulsar.service.ApachePulsarSampleServiceImpl;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author UmutBayram
 */
public class ApachePulsarSample {

    public static void main(String[] args) {
        try {
            PulsarClient pulsarClient = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650")
                    .build();
            ApachePulsarSampleService apachePulsarSampleService = new ApachePulsarSampleServiceImpl(pulsarClient);
            apachePulsarSampleService.producer();
            apachePulsarSampleService.consumer();
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }
}
