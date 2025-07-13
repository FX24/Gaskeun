package com.fx24.bookingservice.repository;

import com.fx24.bookingservice.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
