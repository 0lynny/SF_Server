package org.sfaas.SFaaS.domain.sensorData.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
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

    private SensorData(
        Sensor sensor,
        Short speed,
        Double length,
        Short realPower,
        Short setFrequency,
        Short setDuty,
        Short setPower,
        Short gateOnTime,
        LocalDateTime workingTime
    ) {
        this.sensor = sensor;
        this.speed = speed;
        this.length = length;
        this.realPower = realPower;
        this.setFrequency = setFrequency;
        this.setDuty = setDuty;
        this.setPower = setPower;
        this.gateOnTime = gateOnTime;
        this.workingTime = workingTime;
    }

    public static SensorData fromCsv(CsvDataDto dto, Sensor sensor) {
        return new SensorData(
            sensor,
            dto.speed(),
            dto.length(),
            dto.realPower(),
            dto.setFrequency(),
            dto.setDuty(),
            dto.setPower(),
            dto.gateOnTime(),
            dto.workingTime()
        );
    }
}
