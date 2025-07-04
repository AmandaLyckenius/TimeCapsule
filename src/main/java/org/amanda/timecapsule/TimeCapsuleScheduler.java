package org.amanda.timecapsule;


import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TimeCapsuleScheduler {
    private final TimeCapsuleRepository repository;
    private final MailService mailService;

    public TimeCapsuleScheduler(TimeCapsuleRepository repository, MailService mailService) {
        this.repository = repository;
        this.mailService = mailService;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void deliverTodaysCapsules() throws MessagingException {
        LocalDate today = LocalDate.now();
        List<TimeCapsule> timeCapsulesDue = repository.findByDeliveryDateAndSentFalse(today);

        for(TimeCapsule capsule: timeCapsulesDue){
            mailService.sendCapsuleMail(capsule);
            capsule.setSent(true);
            repository.save(capsule);
        }

    }
}
