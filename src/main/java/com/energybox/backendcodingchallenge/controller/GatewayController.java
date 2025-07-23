package com.energybox.backendcodingchallenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.energybox.backendcodingchallenge.domain.Gateway;

@RestController
@RequestMapping("/gateways")
public class GatewayController {

    @Operation(summary = "create a gateway")
    @PostMapping("")
    public ResponseEntity<Object> createGateway(@RequestBody Gateway gateway) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
