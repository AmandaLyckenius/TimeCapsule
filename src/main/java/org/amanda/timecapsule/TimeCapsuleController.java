package org.amanda.timecapsule;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/time-capsule")
public class TimeCapsuleController {
    private final TimeCapsuleService timeCapsuleService;
    private final MailService mailService;

    public TimeCapsuleController(TimeCapsuleService timeCapsuleService, MailService mailService) {
        this.timeCapsuleService = timeCapsuleService;
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createCapsule(@Valid @RequestBody TimeCapsuleRequest timeCapsuleRequest) throws MessagingException {
        TimeCapsule timeCapsule = timeCapsuleService.createCapsule(timeCapsuleRequest);

        try {
            mailService.sendConfirmationMail(
                    timeCapsuleRequest.getEmail(),
                    "Your vision capsule",
                    "Hi! You have created a time capsule for the future. It will be sent " + timeCapsuleRequest.getDeliveryDate()
            );
        } catch (MailException ex) {
            System.err.println("Email send failed: " + ex.getMessage());
        }
        return ResponseEntity.ok(Map.of("message", "Time capsule sent!"));
    }
}
