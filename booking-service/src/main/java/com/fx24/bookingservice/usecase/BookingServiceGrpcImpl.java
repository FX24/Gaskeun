package com.fx24.bookingservice.usecase;

import com.fx24.bookingservice.application.service.BookingService;
import com.fx24.bookingservice.domain.Booking;
import com.fx24.bookingservice.proto.BookingId;
import com.fx24.bookingservice.proto.BookingRequest;
import com.fx24.bookingservice.proto.BookingResponse;
import com.fx24.bookingservice.proto.BookingServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class BookingServiceGrpcImpl extends BookingServiceGrpc.BookingServiceImplBase {

    private final BookingService bookingService;

    public BookingServiceGrpcImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void createBooking(BookingRequest request, StreamObserver<BookingResponse> responseObserver) {
        try {
            Booking booking = Booking.builder()
                    .userId(request.getUserId())
                    .pickupLocation(request.getPickupLocation())
                    .dropOffLocation(request.getDropoffLocation())
                    .build();
            Booking createdBooking = bookingService.createBooking(booking);

            BookingResponse response = BookingResponse.newBuilder()
                    .setId(createdBooking.getId().toString())
                    .setPickupLocation(createdBooking.getPickupLocation())
                    .setDropoffLocation(createdBooking.getDropOffLocation())
                    .setStatus(createdBooking.getStatus().name())
                    .setCreatedAt(createdBooking.getCreatedAt().toString())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal error occurred: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void getBooking(BookingId request, StreamObserver<BookingResponse> responseObserver) {
        try {

            // TODO : Phase 6
//            UUID id = UUID.fromString(request.getId());
//            Booking booking = bookingService.getBooking(id)
//                    .orElseThrow(() -> new RuntimeException("Booking not found"));
//
//            BookingResponse response = BookingResponse.newBuilder()
//                    .setId(booking.getId().toString())
//                    .setPickupLocation(booking.getPickupLocation())
//                    .setDropoffLocation(booking.getDropOffLocation())
//                    .setStatus(booking.getStatus().name())
//                    .setCreatedAt(booking.getCreatedAt().toString())
//                    .build();
//
//            responseObserver.onNext(response);
//            responseObserver.onCompleted();

            responseObserver.onError(Status.UNIMPLEMENTED.withDescription("Not yet supported").asRuntimeException());
        } catch (IllegalArgumentException e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage())
                    .asRuntimeException());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal error occurred: " + e.getMessage())
                    .asRuntimeException());
        }
    }
}
