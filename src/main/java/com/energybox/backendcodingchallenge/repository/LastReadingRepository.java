package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.domain.LastReading;
import com.energybox.backendcodingchallenge.domain.SensorTypeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastReadingRepository extends JpaRepository<LastReading, SensorTypeKey> {
}
