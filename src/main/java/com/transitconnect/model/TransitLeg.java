package com.transitconnect.model;

public class TransitLeg {
    private String mode;
    private String line;
    private String startStation;
    private String endStation;
    private int numStops;
    private int durationMinutes;
    private int walkDistanceMeters;
    private double fareRupees;
    private String instruction;

    public TransitLeg(String mode, String line, String startStation, String endStation, int numStops, int durationMinutes, int walkDistanceMeters, double fareRupees, String instruction) {
        this.mode = mode;
        this.line = line;
        this.startStation = startStation;
        this.endStation = endStation;
        this.numStops = numStops;
        this.durationMinutes = durationMinutes;
        this.walkDistanceMeters = walkDistanceMeters;
        this.fareRupees = fareRupees;
        this.instruction = instruction;
    }

    public String getMode() { return mode; }
    public String getLine() { return line; }
    public String getStartStation() { return startStation; }
    public String getEndStation() { return endStation; }
    public int getNumStops() { return numStops; }
    public int getDurationMinutes() { return durationMinutes; }
    public int getWalkDistanceMeters() { return walkDistanceMeters; }
    public double getFareRupees() { return fareRupees; }
    public String getInstruction() { return instruction; }
}
