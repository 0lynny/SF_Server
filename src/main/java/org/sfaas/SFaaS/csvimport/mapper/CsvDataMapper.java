package org.sfaas.SFaaS.csvimport.mapper;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.domain.sensor.model.Sensor;
import org.sfaas.SFaaS.domain.sensorData.model.SensorData;

public interface CsvDataMapper {
	CsvDataDto csvToDto(String[] csvLine);
	SensorData DtoToEntity(CsvDataDto csvDto, Sensor sensor);
}
