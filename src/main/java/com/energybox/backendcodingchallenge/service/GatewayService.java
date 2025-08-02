package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.exceptions.DuplicateResourceException;
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

    public Gateway create(Gateway gateway) throws DuplicateResourceException {
        if(this.gatewayRepository.existsByName(gateway.getName())){
            throw new DuplicateResourceException("The gateway name already exists. Please use another name");
        }
        return this.gatewayRepository.save(gateway);
    }

    public List<Gateway> getAll(){
        return this.gatewayRepository.findAll();
    }

    public List<Gateway> getGatewaysWithElectricalSensor(){
        return this.gatewayRepository.findGatewaysWithElectricalSensors();
    }
}
