package com.example.geektrust.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private static final Map<String,ICommand> commandMap=new HashMap<>();

    public void register(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }

    public void executeCommand(String commandName, List<String> tokens){
        ICommand command= commandMap.get(commandName);
        command.execute(tokens);
    }
}
