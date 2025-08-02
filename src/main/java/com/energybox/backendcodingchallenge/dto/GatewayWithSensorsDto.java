package com.energybox.backendcodingchallenge.dto;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import lombok.Data;

import java.util.List;

@Data
public class GatewayWithSensorsDto {
    private Long id;
    private String name;
    private List<Sensor> sensors;

    public static GatewayWithSensorsDto fromEntityObject(Gateway gateway){
        GatewayWithSensorsDto dto = new GatewayWithSensorsDto();
        dto.setId(gateway.getId());
        dto.setName(gateway.getName());
        dto.setSensors(gateway.getSensors());

        return dto;
    }
}
