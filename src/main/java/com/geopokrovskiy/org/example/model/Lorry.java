package com.geopokrovskiy.org.example.model;

public class Lorry extends AbstractAuto{

    public Lorry(long id) {
        this.type = "Lorry";
        this.id = id;
        this.size = 2;
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
