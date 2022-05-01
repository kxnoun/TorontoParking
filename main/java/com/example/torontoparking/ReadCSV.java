package com.example.torontoparking;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class ReadCSV {

    public static ArrayList<ParkingLot> readCSV(InputStream inputStream) {
        ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        try {
            InputStreamReader ip = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(ip);
            String[] nextRecord;
            int bruh = 0;

            while ((nextRecord = csvReader.readNext()) != null) {
                if (bruh == 0) {
                    bruh += 1;
                    continue;
                }
                ParkingLot pl = new ParkingLot(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4], nextRecord[5]);
                parkingLots.add(pl);
            }
            ip.close();
            csvReader.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return parkingLots;
    }
}
