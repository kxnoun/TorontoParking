package com.example.torontoparking;

public class Vehicle {
    String plateNumber, brand, color, size;

    public enum Colors {
        BLACK("BLACK"),
        GRAY("GRAY"),
        WHITE("WHITE"),
        RED("RED"),
        BLUE("BLUE"),
        BROWN("BROWN"),
        GREEN("GREEN"),
        YELLOW("YELLOW"),
        OTHER("OTHER");

        private final String name;

        Colors(String color) {
            name = color;
        }

    }

    public enum Size {
        SMALL("SMALL"),
        MEDIUM("MEDIUM"),
        LARGE("LARGE");

        private final String name;

        Size(String size) {
            name = size;
        }
    }

    public Vehicle(Colors color, Size size, String brand, String plateNumber) {
        this.setColor(color);
        this.setSize(size);
        this.setBrand(brand);
        this.setPlateNumber(plateNumber);
    }

    public void setColor(Colors color) {
        switch (color) {
            case RED:
                this.color = "Red";
                break;
            case BLUE:
                this.color = "Blue";
                break;
            case GRAY:
                this.color = "Gray";
                break;
            case BLACK:
                this.color = "Black";
                break;
            case BROWN:
                this.color = "Brown";
                break;
            case GREEN:
                this.color = "Green";
                break;
            case WHITE:
                this.color = "White";
                break;
            case YELLOW:
                this.color = "Yellow";
                break;
            case OTHER:
                this.color = "Other";
                break;
        }
    }

    public void setSize(Size size) {
        switch (size){
            case LARGE:
                this.size = "Large";
                break;
            case SMALL:
                this.size = "Small";
                break;
            case MEDIUM:
                this.size = "Medium";
                break;
        }
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber.toUpperCase();
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
