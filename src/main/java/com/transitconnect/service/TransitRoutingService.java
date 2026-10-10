package com.transitconnect.service;

import com.transitconnect.model.RouteResponse;
import com.transitconnect.model.TransitLeg;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransitRoutingService {

    public RouteResponse findRoute(String origin, String destination) {
        List<TransitLeg> legs = new ArrayList<>();
        List<double[]> coords = new ArrayList<>();
        String orig = origin != null ? origin.trim() : "";
        String dest = destination != null ? destination.trim() : "";

        // Case 1: GNLU to GIFT City (Violet/Purple Line Branch)
        if (orig.toLowerCase().contains("gnlu") && dest.toLowerCase().contains("gift")) {
            legs.add(new TransitLeg("METRO", "Violet Line (GIFT City Branch)", "GNLU", "GIFT City", 2, 6, 0, 10.0,
                "Board direct Violet Line (Purple Branch) at GNLU towards GIFT City via PDEU (2 stops, 6 mins). No interchange required."));
            legs.add(new TransitLeg("WALK", "Station Exit Walk", "GIFT City Metro Station", "GIFT City Business District", 0, 3, 200, 0.0,
                "Walk 200 meters (3 mins) to corporate towers in GIFT City."));
            
            coords.add(new double[]{23.1543, 72.6475}); // GNLU
            coords.add(new double[]{23.1650, 72.6650}); // PDEU
            coords.add(new double[]{23.1534, 72.6856}); // GIFT City
        }
        // Case 2: Paldi to PDPU
        else if (orig.toLowerCase().contains("paldi") && dest.toLowerCase().contains("pdpu")) {
            legs.add(new TransitLeg("METRO", "Red Line", "Paldi", "Motera Stadium", 9, 23, 0, 15.0, 
                "Board Red Line towards Motera Stadium. Travel 9 stations (23 mins)."));
            legs.add(new TransitLeg("WALK", "Transfer Corridor", "Motera Red Platform", "Motera Yellow Platform", 0, 3, 180, 0.0, 
                "Walk 180 meters (3 mins) to interchange to Yellow Line."));
            legs.add(new TransitLeg("METRO", "Yellow Line (GNLU Branch)", "Motera Stadium", "PDPU", 8, 18, 0, 15.0, 
                "Board Yellow Line towards PDPU/Mahatma Mandir. Travel 8 stations (18 mins)."));
            legs.add(new TransitLeg("WALK", "Campus Pathway", "PDPU Metro Station", "PDPU Main Gate", 0, 2, 150, 0.0, 
                "Walk 150 meters (2 mins) from PDPU Station to Campus Gate."));

            coords.add(new double[]{23.0185, 72.5622});
            coords.add(new double[]{23.0373, 72.5670});
            coords.add(new double[]{23.0965, 72.6008});
            coords.add(new double[]{23.1620, 72.6250});
        } 
        // Case 3: Kalupur Railway Station to Sector 21 Gandhinagar
        else if (orig.toLowerCase().contains("kalupur") && dest.toLowerCase().contains("sector 21")) {
            legs.add(new TransitLeg("METRO", "Blue Line", "Kalupur Railway Station", "Old High Court", 4, 8, 0, 10.0,
                "Board Blue Line towards Thaltej. Travel 4 stations to Old High Court interchange (8 mins)."));
            legs.add(new TransitLeg("WALK", "Interchange", "Blue Platform", "Red Platform", 0, 2, 120, 0.0,
                "Interchange at Old High Court station. Walk 120m (2 mins) to Red Line platform."));
            legs.add(new TransitLeg("METRO", "Red Line", "Old High Court", "Motera Stadium", 7, 16, 0, 15.0,
                "Board Red Line towards Motera Stadium (7 stations, 16 mins)."));
            legs.add(new TransitLeg("PARIVAHAN", "Route 101 (Every 30m)", "Motera Bus Stop", "Sector 21 Bus Depot", 12, 25, 100, 20.0,
                "Board Gandhinagar Parivahan Bus Route 101 (departs every 30 mins) to Sector 21 (25 mins)."));
            legs.add(new TransitLeg("WALK", "Last Mile", "Sector 21 Bus Stop", "Sector 21 Complex", 0, 3, 200, 0.0,
                "Walk 200m (3 mins) to Sector 21 complex."));

            coords.add(new double[]{23.0251, 72.6031});
            coords.add(new double[]{23.0373, 72.5670});
            coords.add(new double[]{23.0965, 72.6008});
            coords.add(new double[]{23.2384, 72.6412});
        } 
        // Default Route
        else {
            legs.add(new TransitLeg("BRTS", "Janmarg Corridor 1", orig, "Interchange Hub", 6, 18, 50, 10.0,
                "Board BRTS Janmarg from " + orig + " towards central transit hub (6 stops, 18 mins)."));
            legs.add(new TransitLeg("WALK", "Transfer", "BRTS Bay", "Metro Station", 0, 4, 250, 0.0,
                "Walk 250 meters (4 mins) to connecting Metro station."));
            legs.add(new TransitLeg("METRO", "Blue/Red Line", "Interchange Hub", dest, 5, 14, 0, 15.0,
                "Board Metro towards " + dest + " (5 stops, 14 mins)."));
            legs.add(new TransitLeg("WALK", "Destination Walk", dest + " Station", dest, 0, 3, 200, 0.0,
                "Walk 200 meters (3 mins) to final destination " + dest + "."));

            coords.add(new double[]{23.0225, 72.5714});
            coords.add(new double[]{23.1000, 72.6000});
        }

        int totalDuration = legs.stream().mapToInt(TransitLeg::getDurationMinutes).sum();
        int totalWalk = legs.stream().mapToInt(TransitLeg::getWalkDistanceMeters).sum();
        double totalFare = legs.stream().mapToDouble(TransitLeg::getFareRupees).sum();

        return new RouteResponse(orig, dest, totalDuration, totalWalk, totalFare, legs, coords);
    }
}
