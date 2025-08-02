package com.energybox.backendcodingchallenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@IdClass(SensorTypeKey.class)
public class LastReading {
    private LocalDateTime timestamp;
    @Id
    private String key;
    private double value;
    @Id
    @ManyToOne
    @JoinColumn(name="sensor_id")
    @JsonIgnore
    private Sensor sensor;
}

