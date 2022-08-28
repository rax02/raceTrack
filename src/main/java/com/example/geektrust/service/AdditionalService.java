package com.example.geektrust.service;

import com.example.geektrust.entity.BookingDetails;
import com.example.geektrust.repository.IBookingDataRepository;
import com.example.geektrust.repository.ITrackAvailabilityTime;
import com.example.geektrust.util.CommonConstants;
import com.example.geektrust.util.TrackType;
import com.example.geektrust.util.VehicleType;

import java.time.LocalTime;
import java.util.Optional;

public class AdditionalService implements IAdditionalService {
    private final IBookingDataRepository bookingDataRepository;
    private final ITrackAvailabilityTime regularTrackAvailability;
    private final ITrackAvailabilityTime vipTrackAvailability;

    public AdditionalService(IBookingDataRepository bookingDataRepository, ITrackAvailabilityTime regularTrackAvailability, ITrackAvailabilityTime vipTrackAvailability) {
        this.bookingDataRepository = bookingDataRepository;
        this.regularTrackAvailability = regularTrackAvailability;
        this.vipTrackAvailability = vipTrackAvailability;
    }

    @Override
    public String extendAdditionalTime(String vehicleNumber, LocalTime extendedTime) {
        if(extendedTime.isBefore(LocalTime.of(20,0).plusMinutes(1)) && extendedTime.isAfter(LocalTime.of(13,0).minusMinutes(1))){
            Optional<BookingDetails> isCurrentBooking = bookingDataRepository.findById(vehicleNumber);
            BookingDetails currentBooking;
            if(isCurrentBooking.isPresent()){
                currentBooking= isCurrentBooking.get();
                TrackType trackType = currentBooking.getTrackType();
                LocalTime entryTime = currentBooking.getExitTime();
                VehicleType vehicleType = currentBooking.getVehicleType();
                BookingDetails tempBooking= new BookingDetails(vehicleType,vehicleNumber,entryTime, extendedTime);
                tempBooking.setTrackType(trackType);

                if(TrackType.REGULAR==trackType){
                    if(regularTrackAvailability.isTrackAvailable(tempBooking)){
                        currentBooking.setExitTime(extendedTime);
                        return CommonConstants.SUCCESS;
                    }
                    return  CommonConstants.RACETRACK_FULL;
                }
                if(TrackType.VIP==trackType){
                    if(vipTrackAvailability.isTrackAvailable(tempBooking)){
                        currentBooking.setExitTime(extendedTime);
                        return CommonConstants.SUCCESS;
                    }
                    return CommonConstants.RACETRACK_FULL;
                }
            }
        }
            return CommonConstants.INVALID_EXIT_TIME;


    }
}
