package com.example.torontoparking;


public class ParkingLot {
    String assetNumber;
    String name;
    String parkingSpaces;
    String handicapSpaces;
    String gis;
    String access;

    public ParkingLot(String assetNumber, String name, String parkingSpaces, String handicapSpaces, String gis, String access) {
        this.assetNumber = assetNumber;
        this.name = name;
        this.parkingSpaces = parkingSpaces;
        this.handicapSpaces = handicapSpaces;
        this.gis = gis;
        this.access = access;
    }
    public ParkingLot() {

    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public void setGis(String gis) {
        this.gis = gis;
    }

    public void setHandicapSpaces(String handicapSpaces) {
        this.handicapSpaces = handicapSpaces;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParkingSpaces(String parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public Double getLat() {
        if (this.gis.equals("")) {
            return null;
        }
        else {
            return Double.parseDouble(gis.split(",")[0].substring(1));
        }
    }

    public Double getLong() {
        if (this.gis.equals("")) {
            return null;
        }
        else {
            String l = gis.split(",")[1];
            return Double.parseDouble(l.substring(0, l.length() - 1));
        }
    }
}
