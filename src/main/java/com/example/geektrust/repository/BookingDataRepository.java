package com.example.geektrust.repository;

import com.example.geektrust.entity.BookingDetails;

import java.util.*;

public class BookingDataRepository implements IBookingDataRepository{

    private final Map<String, BookingDetails> bookingData;

    public BookingDataRepository() {
        this.bookingData = new HashMap<>();
    }

    @Override
    public BookingDetails save(BookingDetails entity) {
        bookingData.put(entity.getVehicleNumber(), entity);
//        bookingData.values().forEach(b-> System.out.println(b.getVehicleNumber()));
        return entity;
    }

    @Override
    public List<BookingDetails> findAll() {
        return new ArrayList<>(this.bookingData.values());
    }

    @Override
    public Optional<BookingDetails> findById(String id) {
        return Optional.ofNullable(this.bookingData.get(id));
    }
}
