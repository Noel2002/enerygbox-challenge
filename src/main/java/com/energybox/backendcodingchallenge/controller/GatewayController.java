package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.dto.GatewayDetailsDto;
import com.energybox.backendcodingchallenge.service.GatewayService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.energybox.backendcodingchallenge.domain.Gateway;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    @Autowired
    public GatewayController(GatewayService gatewayService){
        this.gatewayService = gatewayService;
    }

    @Operation(summary = "create a gateway")
    @PostMapping("")
    public ResponseEntity<GatewayDetailsDto> createGateway(@RequestBody Gateway gateway) {
        Gateway created = this.gatewayService.create(gateway);
        GatewayDetailsDto response = new GatewayDetailsDto();
        response.setId(created.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<GatewayDetailsDto>> getAllGateways(){
        List<Gateway> gateways = this.gatewayService.getAll();
        List<GatewayDetailsDto> response = gateways.stream().map(GatewayDetailsDto::fromEntityObject).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/with-sensors/electrical")
    public ResponseEntity<List<GatewayDetailsDto>> getGatewaysWithElectricalSensors(){
        List<GatewayDetailsDto> response = this.gatewayService.getGatewaysWithElectricalSensor()
                .stream()
                .map(GatewayDetailsDto::fromEntityObject)
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
