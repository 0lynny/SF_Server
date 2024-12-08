package org.sfaas.SFaaS.csvimport.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.csvimport.mapper.CsvDataMapper;
import org.sfaas.SFaaS.csvimport.parser.CsvParser;
import org.sfaas.SFaaS.csvimport.service.CsvImportService;
import org.sfaas.SFaaS.domain.sensorData.model.SensorData;
import org.sfaas.SFaaS.domain.sensorData.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// TODO: 데이터 저장 로직을 SensorDataService에게 맡길지 고민
@Service
@RequiredArgsConstructor
public class WeldingCsvImportService implements CsvImportService {

	private static final int BATCH_SIZE = 1000;

	private final SensorDataRepository sensorDataRepository;
	private final CsvDataMapper csvDataMapper;
	private final CsvParser csvParser;

	@Override
	public void importCsv(InputStream inputStream) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = buffer.readLine();
			List<String> lines = new ArrayList<>(BATCH_SIZE);
			// TODO: sensor 구분 및 생성
			// Sensor tempSensor = new Sensor();

			if (line != null) {
				List<String> header = csvParser.parseHeader(buffer.readLine());
			}

			while ((line = buffer.readLine()) != null) {
				lines.add(line);

				if (lines.size() >= BATCH_SIZE) {
					saveData(lines);
				}

				if (!lines.isEmpty()) {
					saveData(lines);
				}
			}
		}

	}

	// TODO: sensor구분해서 저장 필요
	private void saveData(List<String> lines) {
		List<CsvDataDto> dto = csvParser.parse(lines);
		lines.clear();
		List<SensorData> data = dto.stream()
			.map((d) -> csvDataMapper.DtoToEntity(d, null))
			.toList();
		sensorDataRepository.saveAll(data);
	}
}
