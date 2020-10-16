package com.capgemini.indianStateCensusAnalyserDay18;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVStateCensus {
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException {
		Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
		CsvToBeanBuilder<IndianCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		csvToBeanBuilder.withType(IndianCensusCSV.class);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<IndianCensusCSV> csvToBean = csvToBeanBuilder.build();
		Iterator<IndianCensusCSV> censusCsvIterator = csvToBean.iterator();
		Iterable<IndianCensusCSV> csvIterable = () -> censusCsvIterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;

	}
}
