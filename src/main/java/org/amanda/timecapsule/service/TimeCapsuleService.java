package org.amanda.timecapsule.service;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.amanda.timecapsule.model.TimeCapsule;
import org.amanda.timecapsule.repository.TimeCapsuleRepository;
import org.amanda.timecapsule.model.TimeCapsuleRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeCapsuleService {
    private final TimeCapsuleRepository timeCapsuleRepository;
    private final MailService mailService;

    public TimeCapsuleService(TimeCapsuleRepository timeCapsuleRepository, MailService mailService) {
        this.timeCapsuleRepository = timeCapsuleRepository;
        this.mailService = mailService;
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

    public int deliverTodaysCapsules() {
        LocalDate today = LocalDate.now();
        List<TimeCapsule> timeCapsulesDue = timeCapsuleRepository.findByDeliveryDateAndSentFalse(today);
        int sentCount = 0;

        for(TimeCapsule capsule: timeCapsulesDue){

            try {
                capsule.setSent(true);
                timeCapsuleRepository.save(capsule);
                mailService.sendCapsuleMail(capsule);

                sentCount++;
            } catch (Exception e){
                capsule.setSent(false);
                timeCapsuleRepository.save(capsule);
            }
        }
        return sentCount;
    }
}
