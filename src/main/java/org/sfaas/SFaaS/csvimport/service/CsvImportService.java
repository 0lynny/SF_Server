package org.sfaas.SFaaS.csvimport.service;

import java.io.IOException;
import java.io.InputStream;

public interface CsvImportService {
	void importCsv(InputStream inputStream) throws IOException;
}
