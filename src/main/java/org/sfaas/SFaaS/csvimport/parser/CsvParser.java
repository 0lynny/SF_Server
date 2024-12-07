package org.sfaas.SFaaS.csvimport.parser;

import java.io.InputStream;
import java.util.List;

import org.sfaas.SFaaS.csvimport.dto.CsvDataDto;
import org.sfaas.SFaaS.csvimport.exception.CsvParseException;

public interface CsvParser {

	/**
	 * CSV 파일의 전체 내용을 파싱합니다.
	 * @param inputStream CSV 파일의 InputStream
	 * @return 파싱된 데이터의 리스트
	 * @throws CsvParseException 파싱 중 오류 발생 시
	 */
	List<CsvDataDto> parse(InputStream inputStream) throws CsvParseException;

	/**
	 * CSV 파일의 헤더를 파싱합니다.
	 * @param inputStream CSV 파일의 InputStream
	 * @return 파싱된 헤더 정보
	 * @throws CsvParseException 파싱 중 오류 발생 시
	 */
	List<String> parseHeader(InputStream inputStream) throws CsvParseException;
}
