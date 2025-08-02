package com.energybox.backendcodingchallenge.dto;

import com.energybox.backendcodingchallenge.domain.LastReading;
import com.energybox.backendcodingchallenge.domain.Sensor;
import lombok.Data;

import java.util.List;

@Data
public class SensorWithLastReadings {
    Long id;
    private List<String> types;
    private List<LastReading> lastReadings;

    public static SensorWithLastReadings fromEntity(Sensor sensor){
        SensorWithLastReadings dto = new SensorWithLastReadings();
        dto.setId(sensor.getId());
        dto.setTypes(sensor.getTypes());
        dto.setLastReadings(sensor.getLastReadings());

        return dto;
    }
}
