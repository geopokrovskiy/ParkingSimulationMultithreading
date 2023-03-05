package com.geopokrovskiy.org.example.service;

import com.geopokrovskiy.org.example.model.AbstractAuto;
import com.geopokrovskiy.org.example.util.RandomGenerator;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;


public class LineService implements Callable<Integer> {
    private int lineCapacity;
    private long T1;
    private RandomGenerator randomGenerator = new RandomGenerator();

    private Queue<AbstractAuto> autosWaiting = new ConcurrentLinkedQueue<>();
    private ParkingService parkingService;

    private WriterService writerService;


    public LineService(int lineCapacity, long t1) {
        this.lineCapacity = lineCapacity;
        T1 = t1 * 1000;
    }

    public int getLineCapacity() {
        return lineCapacity;
    }

    public void setLineCapacity(int lineCapacity) {
        this.lineCapacity = lineCapacity;
    }


    public ParkingService getParkingService() {
        return parkingService;
    }

    public void setParkingService(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public WriterService getWriterService() {
        return writerService;
    }

    public void setWriterService(WriterService writerService) {
        this.writerService = writerService;
    }

    public Queue<AbstractAuto> getAutosWaiting() {
        return autosWaiting;
    }

    public void setAutosWaiting(Queue<AbstractAuto> autosWaiting) {
        this.autosWaiting = autosWaiting;
    }

    public synchronized void addToWaitingLine(AbstractAuto auto) throws InterruptedException {
        this.autosWaiting.add(auto);
        String type = "A " + auto.getType();
        System.out.println(type + " with id=<" + auto.getId() + "> is now waiting in the line.");
    }

    public synchronized void parkAuto() throws InterruptedException {
        AbstractAuto auto = this.autosWaiting.peek();
        int vacantCount = this.parkingService.getVacantCount();
        String type = "A " + auto.getType();
        int size = auto.getSize();
        if (vacantCount >= auto.getSize()) {
            System.out.println(type + " with id=<" + auto.getId() + "> has been parked.");
            this.parkingService.setVacantCount(this.parkingService.getVacantCount() - size);
            this.autosWaiting.poll();
            this.parkingService.getAutosParked().add(auto);
        }
    }

    @Override
    public Integer call(){
        try {
            while (this.autosWaiting.size() < this.lineCapacity) {
                AbstractAuto auto = this.randomGenerator.generateAuto();
                this.addToWaitingLine(auto);
                long waitingTime = this.randomGenerator.waitForSomeTime(this.T1);
                this.parkAuto();
                Thread.sleep(waitingTime);
            }
            return 1;
        } catch (InterruptedException ignored) {}
        return 0;
    }
}

