package com.example.geektrust;

import com.example.geektrust.service.RideServiceImpl;
import com.example.geektrust.service.RideShareService;
import com.example.geektrust.utility.CommandParams;
import com.example.geektrust.utility.FileReaderUtility;

import java.util.List;

public class Main {
    public static void main(String[] args) {

       // Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
          /*  FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            }
            sc.close(); // closes the scanner*/
            String filePath=args[0];
            parseAndProcessInputFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void parseAndProcessInputFile(String filePath){
        FileReaderUtility fileReaderObj=new FileReaderUtility(filePath);
        List<CommandParams> commandParams = fileReaderObj.getCommandParams();
        RideShareService rideShareService=new RideServiceImpl();
        rideShareService.processCommand(commandParams);
    }
}
