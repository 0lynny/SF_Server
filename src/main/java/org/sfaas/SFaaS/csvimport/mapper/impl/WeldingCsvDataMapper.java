package org.sfaas.SFaaS.csvimport.mapper.impl;

import java.time.LocalDateTime;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.csvimport.mapper.CsvDataMapper;

import lombok.RequiredArgsConstructor;

import org.sfaas.SFaaS.global.common.constants.WeldingCsvConstants;

@RequiredArgsConstructor
public class WeldingCsvDataMapper implements CsvDataMapper {

	@Override
	public CsvDataDto csvToDto(String[] csvLine) {
		return CsvDataDto.builder()
			.Speed(Short.parseShort(csvLine[1]))
			.Length(Double.parseDouble(csvLine[2]))
			.RealPower(Short.parseShort(csvLine[3]))
			.SetFrequency(Short.parseShort(csvLine[4]))
			.SetDuty(Short.parseShort(csvLine[5]))
			.SetPower(Short.parseShort(csvLine[6]))
			.GateOnTime(Short.parseShort(csvLine[7]))
			.WorkingTime(LocalDateTime.parse(csvLine[8], WeldingCsvConstants.FORMATTER))
			.build();
	}
}