package org.amanda.timecapsule.service;

import jakarta.validation.Valid;
import org.amanda.timecapsule.model.TimeCapsule;
import org.amanda.timecapsule.repository.TimeCapsuleRepository;
import org.amanda.timecapsule.model.TimeCapsuleRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeCapsuleService {
    private final TimeCapsuleRepository timeCapsuleRepository;

    public TimeCapsuleService(TimeCapsuleRepository timeCapsuleRepository) {
        this.timeCapsuleRepository = timeCapsuleRepository;
    }

    public TimeCapsule createCapsule(@Valid TimeCapsuleRequest timeCapsuleRequest) {

        TimeCapsule capsule = new TimeCapsule();
        capsule.setEmail(timeCapsuleRequest.getEmail());
        capsule.setMessage(timeCapsuleRequest.getMessage());
        capsule.setDeliveryDate(timeCapsuleRequest.getDeliveryDate());
        capsule.setCreatedAt(LocalDateTime.now());

        timeCapsuleRepository.save(capsule);

        return capsule;
    }
}
