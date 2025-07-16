package com.fx24.bookingservice.application.port.out;

import com.fx24.bookingservice.event.BookingCreatedEvent;

public interface BookingKafkaProducerPort {
    void publishBookingCreated(BookingCreatedEvent event);
}