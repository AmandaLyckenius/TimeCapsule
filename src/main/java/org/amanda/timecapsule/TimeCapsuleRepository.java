package org.amanda.timecapsule;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface TimeCapsuleRepository extends MongoRepository<TimeCapsule, String> {
    public List<TimeCapsule> findByDeliveryDateAndSentFalse(LocalDate deliveryDate);
}
