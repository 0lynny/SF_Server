package org.sfaas.SFaaS.csvimport.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.csvimport.mapper.CsvDataMapper;
import org.sfaas.SFaaS.csvimport.parser.CsvParser;
import org.sfaas.SFaaS.csvimport.service.CsvImportService;
import org.sfaas.SFaaS.domain.sensor.model.Sensor;
import org.sfaas.SFaaS.domain.sensor.repository.SensorRepository;
import org.sfaas.SFaaS.domain.sensorData.model.SensorData;
import org.sfaas.SFaaS.domain.sensorData.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeldingCsvImportService implements CsvImportService {

	private static final int BATCH_SIZE = 1000;

	private final SensorDataRepository sensorDataRepository;
	private final SensorRepository sensorRepository;
	private final CsvDataMapper csvDataMapper;
	private final CsvParser csvParser;

	@Override
	public void importCsv(InputStream inputStream) throws IOException {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = buffer.readLine();
			List<String> lines = new ArrayList<>(BATCH_SIZE);
			// TODO: sensor 임의로 1번 아이디 센서 고정, sensor 조회 에러...
			Sensor sensor = sensorRepository.findByName("welding_sensor");
			log.error(sensor.toString());

			if (line != null) {
				List<String> header = csvParser.parseHeader(line);
			}

			while ((line = buffer.readLine()) != null) {
				lines.add(line);

				if (lines.size() >= BATCH_SIZE) {
					saveData(lines, sensor);
				}

				if (!lines.isEmpty()) {
					saveData(lines, sensor);
				}
			}
		}

	}

	private void saveData(List<String> lines, Sensor sensor) {
		List<CsvDataDto> dto = csvParser.parse(lines);
		lines.clear();
		List<SensorData> data = dto.stream()
			.map((d) -> csvDataMapper.DtoToEntity(d, sensor))
			.toList();
		sensorDataRepository.saveAll(data);
	}
}
