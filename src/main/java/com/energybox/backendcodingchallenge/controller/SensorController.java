package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.LastReading;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.dto.AssignSensorToGatewayDto;
import com.energybox.backendcodingchallenge.dto.CreateSensorDto;
import com.energybox.backendcodingchallenge.dto.GatewayDetailsDto;
import com.energybox.backendcodingchallenge.dto.SensorDetailsDto;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }
    @Operation(summary = "Get all sensor")
    @GetMapping("")
    public ResponseEntity<List<SensorDetailsDto>> getAllSensors() {
        List<Sensor> sensors = this.sensorService.getAll();
        List<SensorDetailsDto> response = sensors.stream().map(SensorDetailsDto::fromEntityObject).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createSensor(@RequestBody CreateSensorDto createSensorDto){
        try{
            Sensor sensor = new Sensor();
            sensor.setTypes(createSensorDto.types());
            Sensor createdSensor = this.sensorService.create(sensor, createSensorDto.gateway());
            SensorDetailsDto response = SensorDetailsDto.fromEntityObject(createdSensor);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/gateway/{gatewayId}")
    public ResponseEntity<List<SensorDetailsDto>> getSensorsByGateway(@PathVariable Long gatewayId){
        List<SensorDetailsDto> response = this.sensorService.getSensorsByGateway(gatewayId)
                .stream()
                .map(SensorDetailsDto::fromEntityObject)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<SensorDetailsDto>> getSensorsByType(@PathVariable String type){
        List<SensorDetailsDto> response = this.sensorService.getSensorsByType(type)
                .stream()
                .map(SensorDetailsDto::fromEntityObject)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{sensorId}/assign")
    public ResponseEntity<Object> assignSensorToGateway(@PathVariable Long sensorId,
                                                              @RequestBody AssignSensorToGatewayDto assignSensorToGatewayDto) {
        try{
            Sensor sensor = this.sensorService.assignToGateway(
                    sensorId,
                    assignSensorToGatewayDto.getGatewayId());
            SensorDetailsDto response = SensorDetailsDto.fromEntityObject(sensor);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{sensorId}/read")
    public ResponseEntity<List<SensorDetailsDto>> recordReading(@PathVariable Long sensorId,
                                                                        @RequestBody AssignSensorToGatewayDto assignSensorToGatewayDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/{sensorId}/last-readings")
    public ResponseEntity<Object> getSensorLastReading(@PathVariable Long sensorId){
        try{
            List<LastReading> response = this.sensorService.getLastReading(sensorId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}