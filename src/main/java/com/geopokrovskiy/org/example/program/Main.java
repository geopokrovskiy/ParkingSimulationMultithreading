package com.geopokrovskiy.org.example.program;


import com.geopokrovskiy.org.example.model.Parking;

public class Main {

    /**
     * Parking workflow: when starting the application, the user enters the following parameters:
     * - number of parking spaces
     * - the maximum length of the queue of cars waiting to enter the parking lot
     * - interval of generation of incoming cars in seconds
     * - interval of generation of outgoing cars in seconds
     * <p>
     * After entering the parameters, the application starts to generate input cars and put them in the queue.
     * The type of car is chosen arbitrarily, but taking into account the fact that there should be fewer trucks.
     * In other words, trucks are generated less frequently. The generation frequency uses a user-defined interval.
     * That is, if we have an interval T1, then at some random time during this interval,
     * car in the entrance queue. Similarly, the removal of cars from parking spaces.
     * That is, if we have an interval for removing a car from the parking lot T2, then at an arbitrary moment during this interval,
     * arbitrary car from the parking lot and removed.
     * Each car has a unique id. It can be of any type, the main thing is uniqueness.
     * Adding a car to the entrance queue and leaving the parking lot must occur independently of each other.
     * Every 5 seconds, parking reports its statistics in the form:
     * - Free places: X
     * - Places occupied: Y (of which there are so many cars and so many trucks)
     * - Cars waiting in line: Z
     * <p>
     * Informing about events:
     * When adding a car to the queue, the parking lot reports "A car/truck with id = <car ID> has queued for entry."
     * When moving a car to a parking lot, the parking lot reports "Car/truck with id = <car id> parked."
     * When a car leaves the parking lot, it informs about this "Car/truck with id = <car ID> has left the parking lot."
     * <p>
     * How parking behaves in different situations:
     * - If there are no places left, then the generation to the input queue does not stop.
     * - If the input queue has reached the specified maximum, then CARMAGEDDON occurs and the parking exits with "alarm" message. Exiting the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        Parking parking = new Parking(10, 10, 10, 20);
        parking.start();
    }
}