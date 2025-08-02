package com.energybox.backendcodingchallenge.dto;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import lombok.Data;

import java.util.List;

@Data
public class SensorWithGatewayDto {
    private Long id;
    private List<String> types;
    private Gateway gateway;

    public static SensorWithGatewayDto fromEntityObject(Sensor sensor){
        SensorWithGatewayDto dto = new SensorWithGatewayDto();
        dto.setId(sensor.getId());
        dto.setTypes(sensor.getTypes());
        dto.setGateway(sensor.getGateway());
        return dto;
    }
}
