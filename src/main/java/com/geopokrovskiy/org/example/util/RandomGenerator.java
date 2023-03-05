package com.geopokrovskiy.org.example.util;

import com.geopokrovskiy.org.example.model.AbstractAuto;
import com.geopokrovskiy.org.example.model.Car;
import com.geopokrovskiy.org.example.model.Lorry;

import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private static final double autoProbability = 0.7;
    private static final Random random = new Random();

    private static long currentId;

    public RandomGenerator() {
        currentId = 1;
    }

    public synchronized AbstractAuto generateAuto() {
        double value = random.nextDouble();
        if (value <= autoProbability) return new Car(currentId++);
        return new Lorry(currentId++);
    }

    public long waitForSomeTime(long t) {
        double timeFraction = random.nextDouble();
        return (long) (timeFraction * t);
    }

    public synchronized AbstractAuto pickRandomCar(List<AbstractAuto> autos) {
        if (autos.isEmpty()) return null;
        int index = random.nextInt(autos.size());
        return autos.get(index);
    }

}
