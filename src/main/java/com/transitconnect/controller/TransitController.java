package com.transitconnect.controller;

import com.transitconnect.model.RouteResponse;
import com.transitconnect.service.TransitRoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransitController {

    @Autowired
    private TransitRoutingService routingService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/api/route")
    @ResponseBody
    public ResponseEntity<RouteResponse> getRoute(
            @RequestParam(defaultValue = "GNLU") String origin,
            @RequestParam(defaultValue = "GIFT City") String destination) {
        RouteResponse response = routingService.findRoute(origin, destination);
        return ResponseEntity.ok(response);
    }
}
