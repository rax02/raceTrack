package com.example.geektrust.command;

import com.example.geektrust.service.IBookService;
import com.example.geektrust.util.VehicleType;

import java.time.LocalTime;
import java.util.List;

public class BookCommand implements ICommand {

    IBookService bookService;

    public BookCommand(IBookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void execute(List<String> tokens) {

        String bookingResponse = bookService.addBookingDetails(
                VehicleType.valueOf(tokens.get(1)),
                tokens.get(2),
                LocalTime.parse(tokens.get(3))
        );

        System.out.println(bookingResponse);
    }
}
