package com.example.geektrust.service;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.repository.IBookingDataRepository;
import com.example.geektrust.util.TrackType;
import com.example.geektrust.util.VehicleType;

import java.time.temporal.ChronoUnit;

public class RevenueService implements IRevenueService {
    IBookingDataRepository bookingDataRepository;

    public RevenueService(IBookingDataRepository bookingDataRepository) {
        this.bookingDataRepository = bookingDataRepository;
    }

    @Override
    public String calculateRevenue() {
        long regularTrackRevenue = 0;
        long vipTrackRevenue = 0;

        for (BookingDetails bookingDetails : bookingDataRepository.findAll()) {
            long additionalCharges = 0;
            long minutes = bookingDetails.getEntryTime().until(bookingDetails.getExitTime(), ChronoUnit.MINUTES);
            long fixedHours = 3;
            if (minutes > 195) {
                additionalCharges = ((long) Math.ceil(((minutes - 180) / 60.0))) * 50;
            }
            if (TrackType.REGULAR == bookingDetails.getTrackType()) {
                regularTrackRevenue += getRegularCost(bookingDetails.getVehicleType()) * fixedHours + additionalCharges;
            } else if (TrackType.VIP == bookingDetails.getTrackType()) {
                vipTrackRevenue += getVipCost(bookingDetails.getVehicleType()) * fixedHours + additionalCharges;
            }
        }
        return regularTrackRevenue + " " + vipTrackRevenue;
    }

    private Integer getRegularCost(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return 60;
            case CAR:
                return 120;
            case SUV:
                return 200;
            default:
                return Integer.MIN_VALUE;

        }
    }

    private Integer getVipCost(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return 250;
            case SUV:
                return 300;
            default:
                return Integer.MIN_VALUE;

        }
    }
}
