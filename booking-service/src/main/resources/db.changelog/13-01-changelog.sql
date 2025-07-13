-- liquibase formatted sql

-- changeset FX24:1752410653716-1
CREATE TABLE bookings
(
    id                UUID NOT NULL,
    user_id           VARCHAR(255),
    pickup_location   VARCHAR(255),
    drop_off_location VARCHAR(255),
    status            VARCHAR(255),
    created_at        TIMESTAMP,
    updated_at        TIMESTAMP,
    CONSTRAINT pk_bookings PRIMARY KEY (id)
);

-- changeset FX24:1752410653716-2
INSERT INTO bookings (id, user_id, pickup_location, drop_off_location, status, created_at, updated_at)
VALUES ('b1f36c7f-7f2e-4f31-8bfb-9c0c0f4f15d9', 'user1', 'location1', 'location2', 'REQUESTED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('b1f36c7f-7f2e-4f31-8bfb-9c0c0f4f15d8', 'user2', 'location3', 'location4', 'MATCHED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('b1f36c7f-7f2e-4f31-8bfb-9c0c0f4f15d7', 'user3', 'location5', 'location6', 'REQUESTED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);