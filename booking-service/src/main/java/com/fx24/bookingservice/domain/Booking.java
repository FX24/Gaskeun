package com.fx24.bookingservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String userId;
    private String pickupLocation;
    private String dropoffLocation;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        this.status = BookingStatus.REQUESTED;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }
}

