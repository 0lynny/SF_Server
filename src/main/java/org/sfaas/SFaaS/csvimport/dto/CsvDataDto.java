package org.sfaas.SFaaS.csvimport.dto;

import java.time.LocalDateTime;

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
