package org.amanda.timecapsule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeCapsuleRepository extends JpaRepository <TimeCapsule, Long> {
}
