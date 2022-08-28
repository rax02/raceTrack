package com.example.geektrust.service;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.util.VehicleType;

import java.time.LocalTime;

public interface IBookService {
    public String addBookingDetails(VehicleType vehicleType, String vehicleNumber, LocalTime entryTime);
}
