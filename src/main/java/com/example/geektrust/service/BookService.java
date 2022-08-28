package com.example.geektrust.service;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.repository.IBookingDataRepository;
import com.example.geektrust.repository.ITrackAvailabilityTime;
import com.example.geektrust.util.CommonConstants;
import com.example.geektrust.util.TrackType;
import com.example.geektrust.util.VehicleType;

import java.time.LocalTime;

public class BookService implements IBookService {
    private final IBookingDataRepository bookingDataRepository;
    private final ITrackAvailabilityTime regularTrackAvailability;
    private final ITrackAvailabilityTime vipTrackAvailability;

    public BookService(IBookingDataRepository bookingDataRepository, ITrackAvailabilityTime regularTrackAvailability, ITrackAvailabilityTime vipTrackAvailability) {
        this.bookingDataRepository = bookingDataRepository;
        this.regularTrackAvailability = regularTrackAvailability;
        this.vipTrackAvailability = vipTrackAvailability;
    }

    @Override
    public String addBookingDetails(VehicleType vehicleType, String vehicleNumber, LocalTime entryTime) {

//        System.out.println(entryTime.toString() + entryTime.isBefore(LocalTime.of(17, 1)));
        if (entryTime.isAfter(LocalTime.of(12, 59)) && entryTime.isBefore(LocalTime.of(17, 1)))
            {
                LocalTime exitTime = entryTime.plusHours(3);
                BookingDetails bookingDetails = new BookingDetails(vehicleType, vehicleNumber, entryTime, exitTime);
                if (regularTrackAvailability.isTrackAvailable(bookingDetails)) {
                    bookingDetails.setTrackType(TrackType.REGULAR);
                    bookingDataRepository.save(bookingDetails);
//                    System.out.println(bookingDetails.toString());
                    return CommonConstants.SUCCESS;
                } else if ((vehicleType == VehicleType.CAR || vehicleType == VehicleType.SUV)
                        && vipTrackAvailability.isTrackAvailable(bookingDetails)) {
                    bookingDetails.setTrackType(TrackType.VIP);
                    bookingDataRepository.save(bookingDetails);
//                    System.out.println(bookingDetails.toString());
                    return CommonConstants.SUCCESS;

                } else {
                    return CommonConstants.RACETRACK_FULL;
                }
            }
            return CommonConstants.INVALID_ENTRY_TIME;


        }
    }

