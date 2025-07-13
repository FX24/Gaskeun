package com.fx24.bookingservice.usecase;

import com.fx24.bookingservice.domain.Booking;
import com.fx24.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> getBooking(UUID id) {
        return bookingRepository.findById(id);
    }
}
