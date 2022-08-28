package com.example.geektrust.repository;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.util.VehicleType;

import java.time.LocalTime;
import java.util.*;


public class VipTrackAvailabilityTime implements ITrackAvailabilityTime{

    private final Map<String, List<BookingDetails>> carAvailabilityData;
    private final Map<String, List<BookingDetails>> suvAvailabilityData;

    public VipTrackAvailabilityTime() {
        Map<String, List<BookingDetails>> carMap = new HashMap<>();
        carMap.put("SubTrack1", new ArrayList<>());
        this.carAvailabilityData = carMap;

        Map<String, List<BookingDetails>> suvMap = new HashMap<>();
        suvMap.put("SubTrack1", new ArrayList<>());
        this.suvAvailabilityData = suvMap;
    }

    @Override
    public Boolean isTrackAvailable(BookingDetails bookingDetails) {
        switch (bookingDetails.getVehicleType()) {

            case CAR:
                for (List<BookingDetails> bookings : this.carAvailabilityData.values()) {
                    if (isSubTrackAvailable(bookings, bookingDetails)) {
                        bookings.add(bookingDetails);
                        return true;
                    }
                }
                break;

            case SUV:
                for (List<BookingDetails> bookings : this.suvAvailabilityData.values()) {
                    if (isSubTrackAvailable(bookings, bookingDetails)) {
                        bookings.add(bookingDetails);
                        return true;
                    }
                }
                break;
            default:


        }
        return false;
    }
    private Boolean isSubTrackAvailable(List<BookingDetails> bookingList, BookingDetails bookingDetails) {
        if (bookingList.isEmpty()) {
            return true;
        }
        bookingList.sort(new EntryTimeComparator());

        LocalTime entryTime=bookingDetails.getEntryTime();
        LocalTime exitTime= bookingDetails.getExitTime();

        if (entryTime.isAfter(LocalTime.of(12, 59)) && exitTime.isBefore(bookingList.get(0).getEntryTime())) {
            return true;
        }
        if (entryTime.isAfter(bookingList.get(bookingList.size() - 1).getExitTime()) && exitTime.isBefore(LocalTime.of(20, 1))) {
            return true;
        }
        for (int i = 0; i < bookingList.size() - 1; i++) {
            if (entryTime.isAfter(bookingList.get(i).getEntryTime()) && entryTime.isBefore(bookingList.get(i + 1).getEntryTime())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public BookingDetails save(BookingDetails entity) {
        return entity;
    }

    @Override
    public List<BookingDetails> findAll() {
        return null;
    }

    @Override
    public Optional<BookingDetails> findById(VehicleType vehicleType) {
        return Optional.empty();
    }
}
