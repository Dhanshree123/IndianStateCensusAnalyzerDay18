package com.capgemini.indianStateCensusAnalyserDay18;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusData.csv";
	private static final String WRONG_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensus.csv";

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

	@Test
	public void givenIndianCensusDataWithWrongFileShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
