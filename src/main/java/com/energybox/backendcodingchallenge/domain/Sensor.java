package com.energybox.backendcodingchallenge.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sensor")
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    List<String> types;
    @ManyToOne
    @JoinColumn(name = "gateway_id")
    @JsonIgnore
    Gateway gateway;
    @OneToMany(mappedBy = "sensor")
    @JsonIgnore
    private List<LastReading> lastReadings = new ArrayList<>();
}

