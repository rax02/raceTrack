package com.example.geektrust.command;

import com.example.geektrust.service.IRevenueService;

import java.util.List;

public class RevenueCommand implements ICommand{
    IRevenueService revenueService;
    public RevenueCommand(IRevenueService revenueService) {
        this.revenueService=revenueService;
    }

    @Override
    public void execute(List<String> tokens) {
        String revenue = revenueService.calculateRevenue();
        System.out.println(revenue);
    }
}
