package org.sfaas.SFaaS.csvimport.dto;

import java.time.LocalDateTime;

import lombok.Builder;

/**
 * @param speed
 * @param length
 * @param realPower
 * @param setFrequency
 * @param setDuty
 * @param setPower
 * @param gateOnTime
 * @param workingTime
 */
@Builder
public record CsvDataDto(
	Short speed,
	Double length,
	Short realPower,
	Short setFrequency,
	Short setDuty,
	Short setPower,
	Short gateOnTime,
	LocalDateTime workingTime
) {}
