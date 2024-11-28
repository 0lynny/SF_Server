package org.sfaas.SFaaS.domain.logData.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sfaas.SFaaS.domain.sensor.model.Sensor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    private LocalDateTime detectedAt;

    @Enumerated(EnumType.STRING)
    private LogType type; // Define LogType as an enum

    private Short value;

    private Short threshold;
}
