package org.amanda.timecapsule;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
public class TimeCapsuleController {
    private final TimeCapsuleService timeCapsuleService;

    public TimeCapsuleController(TimeCapsuleService timeCapsuleService) {
        this.timeCapsuleService = timeCapsuleService;
    }

    @PostMapping
    public ResponseEntity<String> createCapsule(@Valid @RequestBody TimeCapsuleRequest timeCapsuleRequest){
        timeCapsuleService.createCapsule(timeCapsuleRequest);
        return ResponseEntity.ok("Time capsule has been successfully created!");
    }
}
