package org.amanda.timecapsule.controller;


import org.amanda.timecapsule.service.TimeCapsuleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/internal/cron")
public class CronController {

    @Value("${cron.token}")
    private String cronToken;

    private final TimeCapsuleService service;

    public CronController(TimeCapsuleService service) {
        this.service = service;
    }

    @PostMapping("/deliver-due")
    public ResponseEntity<?> deliverDue(@RequestHeader("X-Cron-Token") String token) {
        if (!cronToken.equals(token)){
            return ResponseEntity.status(401).body(Map.of("error", "unauthorized"));
        }
        int sent = service.deliverTodaysCapsules();
        return ResponseEntity.ok(Map.of("sent", sent));
    }
}
