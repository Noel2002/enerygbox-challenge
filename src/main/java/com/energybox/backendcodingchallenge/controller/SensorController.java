package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.dto.*;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }
    @Operation(summary = "Retrieve all sensors")
    @GetMapping("")
    public ResponseEntity<List<SensorDetailsDto>> getAllSensors() {
        List<Sensor> sensors = this.sensorService.getAll();

        List<SensorDetailsDto> response = sensors.stream().map(SensorDetailsDto::fromEntityObject).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new sensor.")
    @PostMapping("")
    public ResponseEntity<Object> createSensor(@RequestBody CreateSensorDto createSensorDto) throws Exception {
        Sensor sensor = new Sensor();
        sensor.setTypes(createSensorDto.types());

        Sensor createdSensor = this.sensorService.create(sensor, createSensorDto.gateway());

        SensorWithGatewayDto response = SensorWithGatewayDto.fromEntityObject(createdSensor);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve sensors assigned to a specific gateway.")
    @GetMapping("/gateway/{gatewayId}")
    public ResponseEntity<List<SensorDetailsDto>> getSensorsByGateway(@PathVariable Long gatewayId){
        List<SensorDetailsDto> response = this.sensorService.getSensorsByGateway(gatewayId)
                .stream()
                .map(SensorDetailsDto::fromEntityObject)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve sensors of a specific type")
    @GetMapping("/type/{type}")
    public ResponseEntity<List<SensorDetailsDto>> getSensorsByType(@PathVariable String type){
        List<SensorDetailsDto> response = this.sensorService.getSensorsByType(type)
                .stream()
                .map(SensorDetailsDto::fromEntityObject)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Assign sensor to a specific gateway")
    @PutMapping("/{sensorId}/assign")
    public ResponseEntity<Object> assignSensorToGateway(@PathVariable Long sensorId,
                                                          @RequestBody AssignSensorToGatewayDto assignSensorToGatewayDto) throws Exception {
        Sensor sensor = this.sensorService.assignToGateway(
                sensorId,
                assignSensorToGatewayDto.getGatewayId());
        SensorWithGatewayDto response = SensorWithGatewayDto.fromEntityObject(sensor);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Record a specific sensor's reading.")
    @PutMapping("/{sensorId}/readings")
    public ResponseEntity<SensorWithLastReadings> recordReading(@PathVariable Long sensorId,
                                                                        @RequestBody LastReadingDto sensorReading) throws ResourceNotFoundException {
        Sensor sensor = this.sensorService.recordLastReading(
                sensorId,
                sensorReading.getKey(),
                sensorReading.getValue(),
                sensorReading.getTimestamp()
        );

        SensorWithLastReadings response = SensorWithLastReadings.fromEntity(sensor);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve last readings of a specific sensor.")
    @GetMapping("/{sensorId}/last-readings")
    public ResponseEntity<Object> getSensorLastReading(@PathVariable Long sensorId) throws Exception {
        List<LastReadingDto> response = this.sensorService.getLastReading(sensorId)
                .stream()
                .map(LastReadingDto::fromEntity)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}