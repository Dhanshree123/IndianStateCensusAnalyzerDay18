package com.capgemini.indianStateCensusAnalyserDay18;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyzerTest {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusData.csv";

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectRecords() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			int numOfRecords = csvStateCensus.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(36, numOfRecords);

		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
