package com.example.geektrust.repository;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.util.VehicleType;

import java.time.LocalTime;
import java.util.Comparator;

class EntryTimeComparator implements Comparator<BookingDetails> {
    @Override
    public int compare(BookingDetails o1, BookingDetails o2) {
        if(o1.getEntryTime().isBefore(o2.getEntryTime())) return -1;
        else if(o1.getEntryTime().isAfter(o2.getEntryTime())) return 1;
        else return 0;
    }
}

public interface ITrackAvailabilityTime extends CRUDRepository<BookingDetails, VehicleType>{
    public Boolean isTrackAvailable(BookingDetails bookingDetails);
}
