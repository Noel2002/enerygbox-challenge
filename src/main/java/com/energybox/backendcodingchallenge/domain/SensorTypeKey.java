package com.energybox.backendcodingchallenge.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SensorTypeKey implements Serializable {
    Sensor sensor;
    String key;

    public SensorTypeKey(Sensor sensor, String key){
        this.sensor = sensor;
        this.key = key;
    }

    public SensorTypeKey(){}
}
