package com.example.geektrust.command;

import com.example.geektrust.service.IAdditionalService;

import java.time.LocalTime;
import java.util.List;

public class AdditonalCommand implements ICommand {
    IAdditionalService additionalService;

    public AdditonalCommand(IAdditionalService additionalService) {
        this.additionalService = additionalService;
    }

    @Override
    public void execute(List<String> tokens) {
        String response = additionalService.extendAdditionalTime(
                tokens.get(1),
                LocalTime.parse(tokens.get(2)));

        System.out.println(response);
    }
}
