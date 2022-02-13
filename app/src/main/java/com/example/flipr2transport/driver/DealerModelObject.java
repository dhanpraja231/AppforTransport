package com.example.flipr2transport.driver;

public class DealerModelObject {
    String name;
    String material;
    String mobile;
    String weightOfMaterial;
    String quantity;
    String city;
    String state;

    public DealerModelObject(String name, String material, String mobile, String weightOfMaterial, String quantity, String city, String state) {
        this.name = name;
        this.material = material;
        this.mobile = mobile;
        this.weightOfMaterial = weightOfMaterial;
        this.quantity = quantity;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "DealerModelObject{" +
                "name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", mobile='" + mobile + '\'' +
                ", weightOfMaterial='" + weightOfMaterial + '\'' +
                ", quantity='" + quantity + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
