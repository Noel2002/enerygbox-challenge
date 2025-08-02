package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.LastReading;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;
    private final GatewayRepository gatewayRepository;


    @Autowired
    public SensorService(SensorRepository sensorRepository, GatewayRepository gatewayRepository){
        this.sensorRepository = sensorRepository;
        this.gatewayRepository = gatewayRepository;
    }

    public Sensor create(Sensor sensor, Long gatewayId) throws Exception{
        Optional<Gateway> gateway = this.gatewayRepository.findById(gatewayId);
        if(gateway.isEmpty()){
            throw new Exception("Gateway not found");
        }
        sensor.setGateway(gateway.get());
        return this.sensorRepository.save(sensor);

    }

    public Sensor assignToGateway(Long sensorId, Long gatewayId) throws Exception{
        Optional<Gateway> gatewayRes = this.gatewayRepository.findById(gatewayId);
        if(gatewayRes.isEmpty()){
            throw new Exception("Gateway not found");
        }
        Gateway gateway = gatewayRes.get();

        Optional<Sensor> res = this.sensorRepository.findById(sensorId);
        if(res.isEmpty()){
            throw new Exception("Sensor not found");
        }
        Sensor sensor = res.get();
        sensor.setGateway(gateway);
        return this.sensorRepository.save(sensor);
    }

    public List<Sensor> getAll(){
        return this.sensorRepository.findAll();
    }


    public List<Sensor> getSensorsByType(String type){
        return this.sensorRepository.findByTypesContaining(type);
    }

    public List<Sensor> getSensorsByGateway(Long gatewayId){
        return this.sensorRepository.findByGatewayId(gatewayId);
    }

    public List<LastReading> getLastReading(Long id) throws Exception{
        Optional<Sensor> sensor = this.sensorRepository.findById(id);
        if(sensor.isEmpty()){
            throw new Exception("Sensor cannot be found");
        }

        return sensor.get().getLastReadings();

    }
}
