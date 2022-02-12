package com.example.flipr2transport.dealer;

public class DriverModelObject {
    String name;
    String truckNo;
    int mobile;
    int truckCapacity;
    int drivingExperience;
    String transporterName;
    int age;

    public DriverModelObject(String name, String truckNo, int mobile, int truckCapacity, int drivingExperience, String transporterName, int age) {
        this.name = name;
        this.truckNo = truckNo;
        this.mobile = mobile;
        this.truckCapacity = truckCapacity;
        this.drivingExperience = drivingExperience;
        this.transporterName = transporterName;
        this.age = age;
    }
}
