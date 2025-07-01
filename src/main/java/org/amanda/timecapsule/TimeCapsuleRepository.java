package org.amanda.timecapsule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimeCapsuleRepository extends JpaRepository <TimeCapsule, Long> {
    public List<TimeCapsule> findByDeliveryDate(LocalDate deliveryDate);
}
