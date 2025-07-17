package com.fx24.bookingservice.adapter.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fx24.bookingservice.application.port.out.event.BookingKafkaProducer;
import com.fx24.bookingservice.event.BookingCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingKafkaProducerImpl implements BookingKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "booking-created";

    @Override
    public void publishBookingCreated(BookingCreatedEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, event.bookingId(), payload);
            log.info("BookingCreatedEvent published: {}", payload);
        } catch (Exception e) {
            log.error("Failed to publish BookingCreatedEvent", e);
        }
    }
}
