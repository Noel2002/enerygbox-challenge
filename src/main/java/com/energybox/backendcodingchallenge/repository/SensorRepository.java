package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByGatewayId(Long gatewayId);
    @Query(value = "SELECT * FROM sensor where :type = ANY(sensor.types)", nativeQuery = true)
    List<Sensor> findByTypesContaining(@Param("type") String type);
}
