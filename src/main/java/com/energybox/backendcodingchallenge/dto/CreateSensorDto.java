package com.energybox.backendcodingchallenge.dto;

import java.util.List;

public record CreateSensorDto(
        List<String> types,
        Long gateway){}
