package org.sfaas.SFaaS.csvimport.mapper.impl;

import java.time.LocalDateTime;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.csvimport.mapper.CsvDataMapper;
import org.sfaas.SFaaS.domain.sensor.model.Sensor;
import org.sfaas.SFaaS.domain.sensorData.model.SensorData;
import org.sfaas.SFaaS.global.common.constants.WeldingCsvConstants;
import org.springframework.stereotype.Component;

@Component
public class WeldingCsvDataMapper implements CsvDataMapper {

	@Override
	public CsvDataDto csvToDto(String[] csvLine) {
		return CsvDataDto.builder()
			.speed(Short.parseShort(csvLine[1]))
			.length(Double.parseDouble(csvLine[2]))
			.realPower(Short.parseShort(csvLine[3]))
			.setFrequency(Short.parseShort(csvLine[4]))
			.setDuty(Short.parseShort(csvLine[5]))
			.setPower(Short.parseShort(csvLine[6]))
			.gateOnTime(Short.parseShort(csvLine[7]))
			.workingTime(LocalDateTime.parse(csvLine[8], WeldingCsvConstants.FORMATTER))
			.build();
	}

	@Override
	public SensorData DtoToEntity(CsvDataDto csvDto, Sensor sensor) {
		return SensorData.fromCsv(csvDto, sensor);
	}
}