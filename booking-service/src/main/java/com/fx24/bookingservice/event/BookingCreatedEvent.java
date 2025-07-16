package com.fx24.bookingservice.event;

import java.time.Instant;

public record BookingCreatedEvent(
    String bookingId,
    String userId,
    String pickupLocation,
    String dropoffLocation,
    String status,
    Instant createdAt
) {}
