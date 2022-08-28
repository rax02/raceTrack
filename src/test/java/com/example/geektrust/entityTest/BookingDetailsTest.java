package com.example.geektrust.entityTest;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.util.VehicleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("BookingDetails_Entity")
public class BookingDetailsTest {
    @Test
    public void bookingDetailsTest(){
        assertNotNull(new BookingDetails(VehicleType.SUV, "M40", LocalTime.of(13,0),LocalTime.of(20,0)).toString());
    }
}
