package com.example.geektrust;

import com.example.geektrust.appconfiguration.ApplicationConfiguration;
import com.example.geektrust.command.CommandInvoker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void run(String filepath) {

//        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(filepath);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            ApplicationConfiguration appConfig = new ApplicationConfiguration();
            CommandInvoker commandInvoker = appConfig.getCommandInvoker();

            while (sc.hasNextLine()) {
                //Add your code here to process input commands
                String input = sc.nextLine();
                List<String> tokens = Arrays.asList(input.split(" "));
                commandInvoker.executeCommand(tokens.get(0), tokens);
            }

            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        run(args[0]);
    }
}
