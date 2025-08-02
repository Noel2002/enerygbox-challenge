package com.energybox.backendcodingchallenge.dto;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import lombok.Data;

import java.util.List;

@Data
public class GatewayDetailsDto {
    Long id;
    private String name;
    List<Sensor> sensors;

    public static GatewayDetailsDto fromEntityObject(Gateway gateway){
        GatewayDetailsDto dto = new GatewayDetailsDto();
        dto.setId(gateway.getId());
        dto.setName(gateway.getName());
        dto.setSensors(gateway.getSensors());

        return dto;
    }
}
