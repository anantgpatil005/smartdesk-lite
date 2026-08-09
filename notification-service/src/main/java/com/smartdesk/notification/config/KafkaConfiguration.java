package com.smartdesk.notification.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.smartdesk.common.event.TicketCreatedEvent;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    /*
     * Local:
     * localhost:9092
     *
     * Docker:
     * kafka:29092
     *
     * Docker Compose will override this value using:
     *
     * KAFKA_BOOTSTRAP_SERVERS=kafka:29092
     */
    @Value("${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}")
    private String bootstrapServers;


    // ==========================================================
    // CONSUMER CONFIGURATION
    // ==========================================================

    @Bean
    public ConsumerFactory<String, TicketCreatedEvent> consumerFactory() {

        Map<String, Object> props = new HashMap<>();

        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);

        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "notification-group");

        props.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "earliest");

        JsonDeserializer<TicketCreatedEvent> deserializer =
                new JsonDeserializer<>(TicketCreatedEvent.class);

        deserializer.addTrustedPackages("*");

        /*
         * The event class is now coming from the common module.
         *
         * Therefore we don't need the producer's Java class
         * name in Kafka headers.
         */
        deserializer.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer);
    }


    // ==========================================================
    // KAFKA LISTENER CONTAINER
    // ==========================================================

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TicketCreatedEvent>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, TicketCreatedEvent>
                factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}