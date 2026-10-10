package com.transitconnect.model;

import java.util.List;

public class RouteResponse {
    private String origin;
    private String destination;
    private int totalDurationMinutes;
    private int totalWalkingDistanceMeters;
    private double totalFareINR;
    private List<TransitLeg> itinerary;
    private List<double[]> coordinates;

    public RouteResponse(String origin, String destination, int totalDurationMinutes, int totalWalkingDistanceMeters, double totalFareINR, List<TransitLeg> itinerary, List<double[]> coordinates) {
        this.origin = origin;
        this.destination = destination;
        this.totalDurationMinutes = totalDurationMinutes;
        this.totalWalkingDistanceMeters = totalWalkingDistanceMeters;
        this.totalFareINR = totalFareINR;
        this.itinerary = itinerary;
        this.coordinates = coordinates;
    }

    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public int getTotalDurationMinutes() { return totalDurationMinutes; }
    public int getTotalWalkingDistanceMeters() { return totalWalkingDistanceMeters; }
    public double getTotalFareINR() { return totalFareINR; }
    public List<TransitLeg> getItinerary() { return itinerary; }
    public List<double[]> getCoordinates() { return coordinates; }
}
