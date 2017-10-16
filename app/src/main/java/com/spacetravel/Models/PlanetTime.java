package com.spacetravel.Models;

/**
 * Created by matt on 2017-10-15.
 */

public class PlanetTime {
    private String origin, destination, departure, arrival;
    int trip_duration;

    public PlanetTime(String origin, String destination, String departure, String arrival, int trip_duration)
    {
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.trip_duration = trip_duration;
    }

    public String getOrigin()
    {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getTrip_duration() {
        return trip_duration;
    }
}
