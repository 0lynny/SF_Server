package org.sfaas.SFaaS.domain.sensorData.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sfaas.SFaaS.domain.sensor.model.Sensor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    private Short speed;

    private Double length;

    private Short realPower;

    private Short setFrequency;

    private Short setDuty;

    private Short setPower;

    private Short gateOnTime;

    private LocalDateTime workingTime;

}
