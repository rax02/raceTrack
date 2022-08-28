package com.example.geektrust.appconfiguration;

import com.example.geektrust.command.*;
import com.example.geektrust.repository.*;
import com.example.geektrust.service.*;

public class ApplicationConfiguration {

    private final IBookingDataRepository bookingDataRepository= new BookingDataRepository();
    private final ITrackAvailabilityTime regularTrackAvailability = new RegularTrackAvailabilityTime();
    private final ITrackAvailabilityTime vipTrackAvailability= new VipTrackAvailabilityTime();

    private final IBookService bookService=new BookService(bookingDataRepository, regularTrackAvailability, vipTrackAvailability);
    private final IAdditionalService additionalService=new AdditionalService(bookingDataRepository, regularTrackAvailability, vipTrackAvailability);
    private final IRevenueService revenueService=new RevenueService(bookingDataRepository);

    private final ICommand bookCommand=new BookCommand(bookService);
    private final ICommand additionalCommand= new AdditonalCommand(additionalService);
    private final ICommand revenueCommand= new RevenueCommand(revenueService);

    private final CommandInvoker commandInvoker=new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("BOOK", bookCommand);
        commandInvoker.register("ADDITIONAL", additionalCommand);
        commandInvoker.register("REVENUE",revenueCommand);
        return commandInvoker;
    }
}
