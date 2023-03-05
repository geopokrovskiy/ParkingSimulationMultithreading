package com.geopokrovskiy.org.example.model;

public class Car extends AbstractAuto {

    public Car(long id) {
        this.type = "Car";
        this.id = id;
        this.size = 1;
    }

    @Override
    public String toString() {
        return "Car{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", id=" + id +
                '}';
    }
}
