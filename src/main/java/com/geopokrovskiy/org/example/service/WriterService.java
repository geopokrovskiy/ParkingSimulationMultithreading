package com.geopokrovskiy.org.example.service;

public class WriterService implements Runnable {
    private ParkingService parkingService;
    private LineService lineService;

    public WriterService(ParkingService parkingService, LineService lineService) {
        this.parkingService = parkingService;
        this.lineService = lineService;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                System.out.println();
                long numberOfCars = this.parkingService.getAutosParked().
                        stream().
                        filter(o -> o.getType().equalsIgnoreCase("car")).
                        count();
                long numberOfLorries = this.parkingService.getAutosParked().
                        stream().
                        filter(o -> o.getType().equalsIgnoreCase("lorry")).
                        count();
                System.out.println("VACANT PLACES: " + this.parkingService.getVacantCount() + ".\n" +
                        "TAKEN PLACES: " + numberOfCars + " cars, " + numberOfLorries + " lorries.\n" +
                        "WAITING: " + this.lineService.getAutosWaiting().size());
                System.out.println();
                Thread.sleep(5000);
            }
        } catch (InterruptedException ignored) {}
    }
}
