package org.amanda.timecapsule;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeCapsuleService {
    private final TimeCapsuleRepository timeCapsuleRepository;

    public TimeCapsuleService(TimeCapsuleRepository timeCapsuleRepository) {
        this.timeCapsuleRepository = timeCapsuleRepository;
    }

    public void createCapsule(@Valid TimeCapsuleRequest timeCapsuleRequest) {

        TimeCapsule capsule = new TimeCapsule();
        capsule.setEmail(timeCapsuleRequest.getEmail());
        capsule.setMessage(timeCapsuleRequest.getMessage());
        capsule.setDeliveryDate(timeCapsuleRequest.getDeliveryDate());
        capsule.setCreatedAt(LocalDateTime.now());

        timeCapsuleRepository.save(capsule);
    }
}
