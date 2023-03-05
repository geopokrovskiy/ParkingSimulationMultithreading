package com.geopokrovskiy.org.example.model;

import com.geopokrovskiy.org.example.service.ParkingService;
import com.geopokrovskiy.org.example.service.LineService;
import com.geopokrovskiy.org.example.service.WriterService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Parking {
    private ParkingService parkingService;
    private LineService lineService;
    private WriterService writerService;


    public Parking(int parkingCapacity, int lineCapacity, long T1, long T2) {
        this.lineService = new LineService(lineCapacity, T1);
        this.parkingService = new ParkingService(parkingCapacity, T2);
        this.lineService.setParkingService(this.parkingService);
        this.parkingService.setLineService(this.lineService);
        this.writerService = new WriterService(this.parkingService, this.lineService);
        this.lineService.setWriterService(this.writerService);
    }

    public void start() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            Future<Integer> lineServiceFuture = executorService.submit(this.lineService);
            executorService.execute(this.writerService);
            executorService.execute(this.parkingService);
            if(lineServiceFuture.get() == 1){
                executorService.shutdownNow();
                System.out.println("CARMAGEDDON!");
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException ignored) {}
    }
}
