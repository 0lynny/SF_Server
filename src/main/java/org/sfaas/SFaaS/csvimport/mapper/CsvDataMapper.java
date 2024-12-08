package org.sfaas.SFaaS.csvimport.mapper;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;

public interface CsvDataMapper {
	CsvDataDto csvToDto(String[] csvLine);
}
