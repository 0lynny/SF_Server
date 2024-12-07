package org.sfaas.SFaaS.csvimport.exception;

import org.sfaas.SFaaS.global.common.exception.SFaaSException;
import org.sfaas.SFaaS.global.common.response.ErrorType;

public class CsvParseException extends SFaaSException {
	public CsvParseException() {
		super(ErrorType.CSV_VALIDATION);
	}
}
