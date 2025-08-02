package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.domain.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    @Query(value = "SELECT g.id, g.name from gateway g join sensor s on g.id = s.gateway_id where 'electrical' = ANY(s.types)", nativeQuery = true)
    public List<Gateway> findGatewaysWithElectricalSensors();

    public boolean existsByName(String name);
}
