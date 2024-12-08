package org.sfaas.SFaaS.csvimport.parser.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.csvimport.exception.CsvParseException;
import org.sfaas.SFaaS.csvimport.mapper.CsvDataMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeldingCsvParserTest {

	@Mock
	private CsvDataMapper csvDataMapper;

	private WeldingCsvParser weldingCsvParser;

	@BeforeEach
	void setUp() {
		weldingCsvParser = new WeldingCsvParser(csvDataMapper);
	}

	@Test
	@DisplayName("정상 데이터 리턴")
	void parse_validData_shouldReturnParsedList() throws CsvParseException {
		// Given
		List<String> lines = List.of(
			"1,250,241.1,1688,1000,100,82,1154, 2022-01-08 10:11:45.181"
		);
		CsvDataDto expectedDto = CsvDataDto.builder()
			.speed((short) 250)
			.length(241.1)
			.realPower((short) 1688)
			.setFrequency((short) 1000)
			.setDuty((short) 100)
			.setPower((short) 82)
			.gateOnTime((short) 1154)
			.workingTime(LocalDateTime.parse("2022-01-08T10:11:45.181"))
			.build();
		when(csvDataMapper.csvToDto(any())).thenReturn(expectedDto);

		// When
		List<CsvDataDto> result = weldingCsvParser.parse(lines);

		// Then
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(expectedDto, result.get(0));
		verify(csvDataMapper, times(1)).csvToDto(any());
	}

	@Test
	@DisplayName("빈 데이터 리턴")
	void parse_emptyList_shouldReturnEmptyList() throws CsvParseException {
		// Given
		List<String> emptyList = List.of();

		// When
		List<CsvDataDto> result = weldingCsvParser.parse(emptyList);

		// Then
		assertTrue(result.isEmpty());
		verify(csvDataMapper, never()).csvToDto(any());
	}

	@Test
	@DisplayName("유효하지않은 데이터 예외처리")
	void parse_invalidData_shouldThrowException() {
		// Given
		List<String> invalidLines = List.of(
			"invalid,data,line"
		);
		when(csvDataMapper.csvToDto(any())).thenThrow(new RuntimeException("Invalid data"));

		// When & Then
		assertThrows(CsvParseException.class, () -> weldingCsvParser.parse(invalidLines));
	}

	@Test
	@DisplayName("정상 헤더 파싱 통과")
	void parseHeader_validHeader_shouldReturnHeaderList() throws CsvParseException {
		// Given
		String validHeader = "PageNo,Speed,Length,RealPower,SetFrequency,SetDuty,SetPower,GateOnTime,WorkingTime";

		// When
		List<String> result = weldingCsvParser.parseHeader(validHeader);

		// Then
		assertEquals(9, result.size());
		assertEquals("PageNo", result.get(0));
		assertEquals("WorkingTime", result.get(8));
	}

	@Test
	@DisplayName("유효하지않은 헤더 예외처리")
	void parseHeader_invalidHeader_shouldThrowException() {
		// Given
		String invalidHeader = "InvalidHeader1,InvalidHeader2";

		// When & Then
		assertThrows(CsvParseException.class, () -> weldingCsvParser.parseHeader(invalidHeader));
	}

	@Test
	@DisplayName("빈 헤더 예외처리")
	void parseHeader_emptyHeader_shouldThrowException() {
		// Given
		String emptyHeader = "";

		// When & Then
		assertThrows(CsvParseException.class, () -> weldingCsvParser.parseHeader(emptyHeader));
	}

	@Test
	@DisplayName("요구하는 CSV 헤더 정보 리턴")
	void getCsvHeaders_shouldReturnCorrectHeaders() {
		// When
		String[] headers = WeldingCsvParser.getCsvHeaders();

		// Then
		assertEquals(9, headers.length);
		assertEquals("PageNo", headers[0]);
		assertEquals("WorkingTime", headers[8]);
	}
}