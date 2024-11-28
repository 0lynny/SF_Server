package org.sfaas.SFaaS.domain.sensor.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sfaas.SFaaS.domain.factory.model.Factory;
import org.sfaas.SFaaS.domain.logData.model.LogData;
import org.sfaas.SFaaS.domain.sensorData.model.SensorData;
import org.sfaas.SFaaS.domain.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factory_id", nullable = false)
    private Factory factory;

    private String name;

    private LocalDateTime installedAt;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    private List<SensorData> sensorData;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    private List<LogData> logData;

}
