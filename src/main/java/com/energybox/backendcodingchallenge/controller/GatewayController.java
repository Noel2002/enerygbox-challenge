package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.dto.CreateGatewayDto;
import com.energybox.backendcodingchallenge.dto.GatewayDetailsDto;
import com.energybox.backendcodingchallenge.dto.GatewayWithSensorsDto;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.exceptions.DuplicateResourceException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.energybox.backendcodingchallenge.domain.Gateway;

import java.util.List;

@RestController
@RequestMapping("/gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    @Autowired
    public GatewayController(GatewayService gatewayService){
        this.gatewayService = gatewayService;
    }

    @Operation(summary = "create a new gateway")
    @PostMapping("")
    public ResponseEntity<GatewayDetailsDto> createGateway(@RequestBody CreateGatewayDto gatewayDto) throws DuplicateResourceException {
        Gateway gateway = new Gateway();
        gateway.setName(gatewayDto.getName());

        Gateway created = this.gatewayService.create(gateway);
        GatewayDetailsDto response = GatewayDetailsDto.fromEntityObject(created);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Retrieve all gateways")
    @GetMapping("")
    public ResponseEntity<List<GatewayDetailsDto>> getAllGateways(){
        List<Gateway> gateways = this.gatewayService.getAll();

        List<GatewayDetailsDto> response = gateways.stream().map(GatewayDetailsDto::fromEntityObject).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Retrieve gateways with electrical sensors. Electrical sensors are sensors with \"electrical\" type.")
    @GetMapping("/with-sensors/electrical")
    public ResponseEntity<List<GatewayWithSensorsDto>> getGatewaysWithElectricalSensors(){
        List<GatewayWithSensorsDto> response = this.gatewayService.getGatewaysWithElectricalSensor()
                .stream()
                .map(GatewayWithSensorsDto::fromEntityObject)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
