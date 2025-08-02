package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.LastReading;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorTypeKey;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.LastReadingRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import exceptions.ResourceNotFoundException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;
    private final GatewayRepository gatewayRepository;
    private final LastReadingRepository lastReadingRepository;


    @Autowired
    public SensorService(
            SensorRepository sensorRepository,
            GatewayRepository gatewayRepository,
            LastReadingRepository lastReadingRepository
    ){
        this.sensorRepository = sensorRepository;
        this.gatewayRepository = gatewayRepository;
        this.lastReadingRepository = lastReadingRepository;
    }

    public Sensor create(Sensor sensor, Long gatewayId) throws Exception{
        Optional<Gateway> gateway = this.gatewayRepository.findById(gatewayId);
        if(gateway.isEmpty()){
            throw new ResourceNotFoundException("Gateway not found");
        }
        sensor.setGateway(gateway.get());
        return this.sensorRepository.save(sensor);

    }

    public Sensor assignToGateway(Long sensorId, Long gatewayId) throws Exception{
        Optional<Gateway> gatewayRes = this.gatewayRepository.findById(gatewayId);
        if(gatewayRes.isEmpty()){
            throw new ResourceNotFoundException("Gateway not found");
        }
        Gateway gateway = gatewayRes.get();

        Optional<Sensor> res = this.sensorRepository.findById(sensorId);
        if(res.isEmpty()){
            throw new ResourceNotFoundException("Sensor not found");
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
            throw new ResourceNotFoundException("Sensor cannot be found");
        }

        return sensor.get().getLastReadings();

    }

    public LastReading recordLastReading(Long sensorId, String key, double value, LocalDateTime timestamp) throws ResourceNotFoundException {
        Sensor sensor = this.sensorRepository.findById(sensorId).orElseThrow(()-> new ResourceNotFoundException("Sensor not found"));
        if(!sensor.getTypes().contains(key)) throw new ResourceNotFoundException("The sensor does not have the specified type");
        SensorTypeKey compositeKey = new SensorTypeKey(sensor, key);
        Optional<LastReading> results = this.lastReadingRepository.findById(compositeKey);

        LastReading record;
        if(results.isEmpty()){
            record = new LastReading();
            record.setSensor(sensor);
            record.setKey(key);
            record.setValue(value);
            record.setTimestamp(timestamp);
        }
        else{
            record = results.get();
            record.setTimestamp(timestamp);
            record.setValue(value);
        }

        return this.lastReadingRepository.save(record);
    }
}
