package org.sfaas.SFaaS.csvimport.dto;

import java.time.LocalDateTime;

import lombok.Builder;

/**
 * @param Speed
 * @param Length
 * @param RealPower
 * @param SetFrequency
 * @param SetDuty
 * @param SetPower
 * @param GateOnTime
 * @param WorkingTime
 */
@Builder
public record CsvDataDto(
	Short Speed,
	Double Length,
	Short RealPower,
	Short SetFrequency,
	Short SetDuty,
	Short SetPower,
	Short GateOnTime,
	LocalDateTime WorkingTime
) {}
