package com.geopokrovskiy.org.example.service;

import com.geopokrovskiy.org.example.model.AbstractAuto;
import com.geopokrovskiy.org.example.util.RandomGenerator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingService implements Runnable {
    private int vacantCount;
    private int parkingCapacity;

    private long T2;

    private RandomGenerator randomGenerator = new RandomGenerator();
    private List<AbstractAuto> autosParked = new CopyOnWriteArrayList<>();
    private LineService lineService;

    public ParkingService(int parkingCapacity, long T2) {
        this.parkingCapacity = parkingCapacity;
        this.T2 = T2 * 1000;
        this.vacantCount = this.parkingCapacity;
    }

    public int getVacantCount() {
        return vacantCount;
    }

    public void setVacantCount(int vacantCount) {
        this.vacantCount = vacantCount;
    }


    public LineService getLineService() {
        return lineService;
    }

    public void setLineService(LineService lineService) {
        this.lineService = lineService;
    }

    public List<AbstractAuto> getAutosParked() {
        return autosParked;
    }

    public void setAutosParked(List<AbstractAuto> autosParked) {
        this.autosParked = autosParked;
    }

    public synchronized void leaveParking(AbstractAuto auto) {
        if (auto != null) {
            int size = auto.getSize();
            String type = "A " + auto.getType();
            System.out.println(type + " with id=<" + auto.getId() + "> has left the parking.");
            this.vacantCount += size;
            this.autosParked.remove(auto);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                long waitingTime = this.randomGenerator.waitForSomeTime(this.T2);
                AbstractAuto auto = this.randomGenerator.pickRandomCar(this.autosParked);
                this.leaveParking(auto);
                Thread.sleep(waitingTime);
            }
        } catch (InterruptedException ignored) {}
    }
}
