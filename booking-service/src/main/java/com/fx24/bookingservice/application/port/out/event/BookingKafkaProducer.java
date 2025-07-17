package com.fx24.bookingservice.application.port.out.event;

import com.fx24.bookingservice.event.BookingCreatedEvent;

public interface BookingKafkaProducer {
    void publishBookingCreated(BookingCreatedEvent event);
}