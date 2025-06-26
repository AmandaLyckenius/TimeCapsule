package org.amanda.timecapsule;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
public class TimeCapsuleController {
    private final TimeCapsuleService timeCapsuleService;

    public TimeCapsuleController(TimeCapsuleService timeCapsuleService) {
        this.timeCapsuleService = timeCapsuleService;
    }
}
