package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Sensor;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {


    @Operation(summary = "Get all sensor")
    @GetMapping
    public List<Sensor> getAllSensors() {
        return null;// return list of sensor
    }

}