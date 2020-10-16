package com.capgemini.indianStateCensusAnalyserDay18;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusData.csv";
	private static final String WRONG_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensus.csv";
	private static final String WRONG_TYPE_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusData.txt";
	private static final String WRONG_DELIMITER_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusWrongDelimiter.csv";
	private static final String WRONG_HEADER_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusWrongHeader.csv";

	private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCode.csv";
	private static final String STATE_CODE_WRONG_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCodeData.csv";

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

	@Test
	public void givenIndianCensusDataWithWrongFileTypeShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaCensusData(WRONG_TYPE_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndianCensusDataWithWrongDelimiterShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaCensusData(WRONG_DELIMITER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_DELIMITER_TYPE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndianCensusDataWithWrongHeaderShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaCensusData(WRONG_HEADER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_HEADER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			int numOfRecords = csvStateCensus.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
			Assert.assertEquals(36, numOfRecords);

		} catch (CensusAnalyserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenIndianStateCodeWithWrongFileShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaStateCodeData(STATE_CODE_WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
