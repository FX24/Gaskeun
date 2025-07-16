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

    public Booking(UUID id, String userId, String pickupLocation, String dropOffLocation) {
        this.id = id;
        this.userId = userId;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String userId;
    private String pickupLocation;
    private String dropOffLocation;

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

