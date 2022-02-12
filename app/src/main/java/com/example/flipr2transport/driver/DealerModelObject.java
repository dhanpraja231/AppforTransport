package com.example.flipr2transport.driver;

public class DealerModelObject {
    String name;
    String material;
    int mobile;
    int weightOfMaterial;
    int quantity;
    String city;
    String state;

    public DealerModelObject(String name, String material, int mobile, int weightOfMaterial, int quantity, String city, String state) {
        this.name = name;
        this.material = material;
        this.mobile = mobile;
        this.weightOfMaterial = weightOfMaterial;
        this.quantity = quantity;
        this.city = city;
        this.state = state;
    }
}
