package com.fx24.bookingservice.application.service;

import com.fx24.bookingservice.application.port.out.event.BookingKafkaProducer;
import com.fx24.bookingservice.domain.Booking;
import com.fx24.bookingservice.event.BookingCreatedEvent;
import com.fx24.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingKafkaProducer kafkaProducer;

    public Booking createBooking(Booking request) {
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .pickupLocation(request.getPickupLocation())
                .dropOffLocation(request.getDropOffLocation())
                .build();

        bookingRepository.save(booking);

        // ✅ Publish event
        BookingCreatedEvent event = new BookingCreatedEvent(
                booking.getId().toString(),
                booking.getUserId(),
                booking.getPickupLocation(),
                booking.getDropOffLocation(),
                booking.getStatus().name(),
                booking.getCreatedAt()
        );
        kafkaProducer.publishBookingCreated(event);

        return booking;
    }

    public Optional<Booking> getBooking(UUID id) {
        return bookingRepository.findById(id);
    }
}
