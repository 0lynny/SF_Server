package org.sfaas.SFaaS.csvimport.controller;

import org.sfaas.SFaaS.csvimport.parser.CsvParser;
import org.sfaas.SFaaS.csvimport.service.CsvImportService;
import org.sfaas.SFaaS.global.common.response.ErrorType;
import org.sfaas.SFaaS.global.common.response.SFaaSResponse;
import org.sfaas.SFaaS.global.common.response.SuccessType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/csv")
@RequiredArgsConstructor
public class CsvImportController {

	private final CsvImportService csvImportService;
	private final CsvParser csvParser;

	@PostMapping("/welding")
	public SFaaSResponse<?> postWeldingCsv(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			return SFaaSResponse.error(ErrorType.CSV_VALIDATION);
		}

		try {
			csvImportService.importCsv(file.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return SFaaSResponse.error(ErrorType.CSV_VALIDATION);
		}

		return SFaaSResponse.success(SuccessType.OK);
	}
}
