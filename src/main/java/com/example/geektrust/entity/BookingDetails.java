package com.example.geektrust.entity;

import com.example.geektrust.util.TrackType;
import com.example.geektrust.util.VehicleType;

import java.time.LocalTime;

public class BookingDetails {
    private final VehicleType vehicleType;
    private final String vehicleNumber;
    private TrackType trackType;
    private final LocalTime entryTime;
    private LocalTime exitTime;

    public void setTrackType(TrackType trackType) {
        this.trackType = trackType;
    }

    @Override
    public String toString() {
        return "BookingDetails{" +
                "vehicleType=" + vehicleType +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", trackType=" + trackType +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                '}';
    }

    public BookingDetails(VehicleType vehicleType, String vehicleNumber, LocalTime entryTime, LocalTime exitTime) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = entryTime;
        this.exitTime= exitTime;

    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public TrackType getTrackType() {
        return trackType;
    }
}
