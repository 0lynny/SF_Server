package org.sfaas.SFaaS.domain.sensor.repository;

import org.sfaas.SFaaS.domain.sensor.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
	Sensor findByName(final String name);
}
