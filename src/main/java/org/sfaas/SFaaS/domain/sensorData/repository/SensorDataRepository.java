package org.sfaas.SFaaS.domain.sensorData.repository;

import org.sfaas.SFaaS.domain.sensorData.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
