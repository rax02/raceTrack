package com.example.geektrust.serviceTest;
import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.repository.BookingDataRepository;
import com.example.geektrust.repository.IBookingDataRepository;
import com.example.geektrust.repository.RegularTrackAvailabilityTime;
import com.example.geektrust.repository.VipTrackAvailabilityTime;
import com.example.geektrust.service.BookService;
import com.example.geektrust.service.RevenueService;
import com.example.geektrust.util.TrackType;
import com.example.geektrust.util.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("RevenueServiceTest")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RevenueServiceTest {
    RevenueService revenueService;
    BookingDataRepository bookingDataRepository;

    @BeforeEach
    void setUp() {
        bookingDataRepository = Mockito.mock(BookingDataRepository.class);
        revenueService = new RevenueService(bookingDataRepository);
    }

    @Test
    @DisplayName("testing calculateRevenue: CAR_regularTrack")
    public void calculateRevenue(){
        VehicleType vehicleType = VehicleType.CAR;
        String vehicleNumber = "A32";
        LocalTime entryTime = LocalTime.of(17, 1);
        List<BookingDetails> bookingList = new ArrayList<>();
        BookingDetails booking =new BookingDetails(vehicleType,vehicleNumber,entryTime,entryTime.plusHours(3));
        booking.setTrackType(TrackType.REGULAR);
        bookingList.add(booking);

        Mockito.when(bookingDataRepository.findAll()).thenReturn(bookingList);
        String response = revenueService.calculateRevenue();
        assertEquals("360 0", response);
    }

    @Test
    @DisplayName("testing calculateRevenue: SUV_vipTrack")
    public void calculateRevenue1(){
        VehicleType vehicleType = VehicleType.SUV;
        String vehicleNumber = "X45";
        LocalTime entryTime = LocalTime.of(15, 1);
        List<BookingDetails> bookingList = new ArrayList<>();
        BookingDetails booking =new BookingDetails(vehicleType,vehicleNumber,entryTime,entryTime.plusHours(3));
        booking.setTrackType(TrackType.VIP);
        bookingList.add(booking);

        Mockito.when(bookingDataRepository.findAll()).thenReturn(bookingList);
        String response = revenueService.calculateRevenue();
        assertEquals("0 900", response);
    }
}
