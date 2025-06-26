package org.amanda.timecapsule;

import org.springframework.stereotype.Service;

@Service
public class TimeCapsuleService {
    private final TimeCapsuleRepository timeCapsuleRepository;

    public TimeCapsuleService(TimeCapsuleRepository timeCapsuleRepository) {
        this.timeCapsuleRepository = timeCapsuleRepository;
    }
}
