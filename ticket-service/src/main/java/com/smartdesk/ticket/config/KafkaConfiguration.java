package com.smartdesk.ticket.config;

import com.smartdesk.common.event.TicketCreatedEvent;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    /*
     * Local:
     * localhost:9092
     *
     * Docker:
     * kafka:29092
     *
     * Docker Compose will provide:
     *
     * KAFKA_BOOTSTRAP_SERVERS=kafka:29092
     *
     * If the environment variable is not present,
     * localhost:9092 will be used.
     */
    @Value("${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}")
    private String bootstrapServers;


    // ==========================================================
    // PRODUCER CONFIGURATION
    // ==========================================================

    @Bean
    public ProducerFactory<String, TicketCreatedEvent> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();

        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);

        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);

        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }


    // ==========================================================
    // KAFKA TEMPLATE
    // ==========================================================

    @Bean
    public KafkaTemplate<String, TicketCreatedEvent> kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());
    }
}