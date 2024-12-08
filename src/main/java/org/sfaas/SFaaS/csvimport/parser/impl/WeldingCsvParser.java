package org.sfaas.SFaaS.csvimport.parser.impl;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.csvimport.exception.CsvParseException;
import org.sfaas.SFaaS.csvimport.mapper.CsvDataMapper;
import org.sfaas.SFaaS.csvimport.parser.CsvParser;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeldingCsvParser implements CsvParser {

	private static final String[] CSV_HEADERS = {
		"PageNo", "Speed", "Length", "RealPower", "SetFrequency",
		"SetDuty", "SetPower", "GateOnTime", "WorkingTime"
	};
	private final CsvDataMapper csvDataMapper;

	@Override
	public List<CsvDataDto> parse(List<String> lines) throws CsvParseException {
		List<CsvDataDto> result = new ArrayList<>(lines.size());

		if (lines.isEmpty()) {
			return Collections.emptyList();
		}

		try (CSVReader csvReader = new CSVReaderBuilder(
			new StringReader(String.join("\n", lines)))
			.withCSVParser(new CSVParserBuilder()
				.withSeparator(',')
				.build())
			.build()) {
			String[] nextLine;
			while((nextLine = csvReader.readNext()) != null) {
				CsvDataDto dto = csvDataMapper.csvToDto(nextLine);
				result.add(dto);
			}
		} catch (Exception e) {
			throw new CsvParseException();
		}
		return result;
	}

	@Override
	public List<String> parseHeader(String line) throws CsvParseException {
		try (CSVReader csvReader = new CSVReaderBuilder(new StringReader(line))
			.withCSVParser(new CSVParserBuilder()
				.withSeparator(',')
				.build())
			.build()) {

			String[] header = csvReader.readNext();
			if (header == null || !isValidHeader(header)) {
				throw new CsvParseException();
			}
			return Arrays.asList(header);
		} catch (Exception e) {
			throw new CsvParseException();
		}
	}

	public static String[] getCsvHeaders() {
		return CSV_HEADERS;
	}

	private boolean isValidHeader(String[] header) {
		if (CSV_HEADERS.length != header.length) {
			return false;
		}

		for (int i = 0; i < CSV_HEADERS.length; i++) {
			if (!CSV_HEADERS[i].equals(header[i])) {
				return false;
			}
		}
		return true;
	}
}
