package com.example.geektrust.serviceTest;
import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.repository.BookingDataRepository;
import com.example.geektrust.repository.RegularTrackAvailabilityTime;
import com.example.geektrust.repository.VipTrackAvailabilityTime;
import com.example.geektrust.service.AdditionalService;
import com.example.geektrust.util.CommonConstants;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("AdditionalServiceTest")
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdditionalServiceTest {
    AdditionalService additionalService;
    BookingDataRepository bookingDataRepository;
    RegularTrackAvailabilityTime regularTrackAvailabilityTime;
    VipTrackAvailabilityTime vipTrackAvailabilityTime;

    @BeforeEach
    void setUp() {
        bookingDataRepository = Mockito.mock(BookingDataRepository.class);
        regularTrackAvailabilityTime = Mockito.mock(RegularTrackAvailabilityTime.class);
        vipTrackAvailabilityTime = Mockito.mock(VipTrackAvailabilityTime.class);
        additionalService = new AdditionalService(bookingDataRepository,regularTrackAvailabilityTime,vipTrackAvailabilityTime);
    }

    @Test
    @DisplayName("testing extendAdditionalTime: invalid_exit_time")
    public void extendAdditionalTime_invalidTime(){
        String vehicleNumber = "A32";
        LocalTime exitTime = LocalTime.of(22, 10);
        String response = additionalService.extendAdditionalTime(vehicleNumber,exitTime);
        assertEquals(CommonConstants.INVALID_EXIT_TIME,response);
    }

    @Test
    @DisplayName("testing extendAdditionalTime: race_track_full")
    public void extendAdditionalTime_raceTrackFull(){
        String vehicleNumber = "A32";
        LocalTime exitTime = LocalTime.of(17, 10);
        String response = additionalService.extendAdditionalTime(vehicleNumber,exitTime);
        assertEquals(CommonConstants.INVALID_EXIT_TIME,response);
    }

    @Test
    @DisplayName("testing extendAdditionalTime: extended_successfully")
    public void extendAdditionalTime_successful(){
        VehicleType vehicleType = VehicleType.CAR;
        String vehicleNumber = "A32";
        LocalTime entryTime = LocalTime.of(17,0);
        LocalTime exitTime = LocalTime.of(17, 50);
        BookingDetails extendedBooking= new BookingDetails(vehicleType,vehicleNumber,entryTime,exitTime);
        extendedBooking.setTrackType(TrackType.REGULAR);
        Optional<BookingDetails> bookingDetail= Optional.of(extendedBooking);
        Mockito.when(bookingDataRepository.findById(Mockito.any())).thenReturn(bookingDetail);
        Mockito.when(regularTrackAvailabilityTime.isTrackAvailable(Mockito.any())).thenReturn(true);
        String response = additionalService.extendAdditionalTime(vehicleNumber,exitTime);
        assertEquals(CommonConstants.SUCCESS,response);
    }

}
