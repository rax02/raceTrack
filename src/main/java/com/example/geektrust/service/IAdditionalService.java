package com.example.geektrust.service;

import java.time.LocalTime;

public interface IAdditionalService {
    public String extendAdditionalTime(String vehicleNumber, LocalTime exitTime);
}
