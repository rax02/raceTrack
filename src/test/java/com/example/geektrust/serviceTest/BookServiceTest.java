package com.example.geektrust.serviceTest;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.repository.BookingDataRepository;
import com.example.geektrust.repository.RegularTrackAvailabilityTime;
import com.example.geektrust.repository.VipTrackAvailabilityTime;
import com.example.geektrust.service.BookService;

import com.example.geektrust.util.CommonConstants;
import com.example.geektrust.util.VehicleType;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BookServiceTest")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServiceTest {
    BookService bookService;
    BookingDataRepository bookingDataRepository;
    RegularTrackAvailabilityTime regularTrackAvailabilityTime;
    VipTrackAvailabilityTime vipTrackAvailabilityTime;

    @BeforeEach
    void setUp() {
        bookingDataRepository = Mockito.mock(BookingDataRepository.class);
        regularTrackAvailabilityTime = Mockito.mock(RegularTrackAvailabilityTime.class);
        vipTrackAvailabilityTime = Mockito.mock(VipTrackAvailabilityTime.class);
        bookService = new BookService(bookingDataRepository,regularTrackAvailabilityTime,vipTrackAvailabilityTime);
    }

    @Test
    @DisplayName("testing addBooking: invalid_entry_time")
    public void addBooking_invalidEntryTime() {
        VehicleType vehicleType = VehicleType.CAR;
        String vehicleNumber = "A32";
        LocalTime entryTime = LocalTime.of(17, 1);

        String response = bookService.addBookingDetails(vehicleType, vehicleNumber, entryTime);

        assertEquals(CommonConstants.INVALID_ENTRY_TIME, response);
    }
    @Test
    @DisplayName("testing addBooking: valid_entry_time race_track_Full")
    public void addBooking_raceTrackFull() {
        VehicleType vehicleType = VehicleType.CAR;
        String vehicleNumber = "A32";
        LocalTime entryTime = LocalTime.of(13, 10);

        String response = bookService.addBookingDetails(vehicleType, vehicleNumber, entryTime);

        assertEquals(CommonConstants.RACETRACK_FULL, response);
    }

    @Test
    @DisplayName("testing addBooking: valid_entry_time regular_track_unavailable")
    public void addBooking_successful_vip() {
        VehicleType vehicleType = VehicleType.CAR;
        String vehicleNumber = "A32";
        LocalTime entryTime = LocalTime.of(13, 10);
        Mockito.when(vipTrackAvailabilityTime.isTrackAvailable(Mockito.any())).thenReturn(true);
        String response = bookService.addBookingDetails(vehicleType, vehicleNumber, entryTime);

        assertEquals(CommonConstants.SUCCESS, response);
    }
    @Test
    @DisplayName("testing addBooking: valid_entry_time regular_track_available")
    public void addBooking_successful_regular() {
        VehicleType vehicleType = VehicleType.CAR;
        String vehicleNumber = "A32";
        LocalTime entryTime = LocalTime.of(13, 10);

        Mockito.when(regularTrackAvailabilityTime.isTrackAvailable(Mockito.any())).thenReturn(true);
        String response = bookService.addBookingDetails(vehicleType, vehicleNumber, entryTime);

        assertEquals(CommonConstants.SUCCESS, response);
    }



}
