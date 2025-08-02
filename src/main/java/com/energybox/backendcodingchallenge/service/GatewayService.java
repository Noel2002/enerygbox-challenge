package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayService {
    private final GatewayRepository gatewayRepository;
    @Autowired
    public GatewayService(GatewayRepository gatewayRepository){
        this.gatewayRepository = gatewayRepository;
    }

    public Gateway create(Gateway gateway){
        return this.gatewayRepository.save(gateway);
    }

    public List<Gateway> getAll(){
        return this.gatewayRepository.findAll();
    }

    public List<Gateway> getGatewaysWithElectricalSensor(){
        return this.gatewayRepository.findGatewaysWithElectricalSensors();
    }
}
