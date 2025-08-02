package com.energybox.backendcodingchallenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "gateway")
@Data
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String name;
    @OneToMany(mappedBy = "gateway")
    @JsonIgnore
    List<Sensor> sensors;
}
