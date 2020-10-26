package com.capgemini.indianStateCensusAnalyserDay18;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.openCSVBuilder.CSVException;

public class CensusAnalyzerTest {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusData.csv";
	private static final String WRONG_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensus.csv";
	private static final String WRONG_TYPE_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusData.txt";
	private static final String WRONG_DELIMITER_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusWrongDelimiter.csv";
	private static final String WRONG_HEADER_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCensusWrongHeader.csv";

	private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCode.csv";
	private static final String STATE_CODE_WRONG_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCodeData.csv";
	private static final String STATE_CODE_WRONG_TYPE_FILE_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCode.txt";
	private static final String STATE_CODE_WRONG_DELIMITER_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCodeWrongDelimiter.csv";
	private static final String STATE_CODE_WRONG_HEADER_PATH = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\StateCodeWrongHeader.csv";

	@Test
	public void givenIndianCensusCSVFileReturnsCorrectRecords() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			int numOfRecords = csvStateCensus.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(36, numOfRecords);

		} catch (CensusAnalyserException e) {
		} catch (IOException e) {
		} catch (CSVException e) {
		}
	}

	@Test
	public void givenIndianCensusDataWithWrongFileShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			csvStateCensus.loadIndiaCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (IOException e) {

		} catch (CSVException e) {

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

		} catch (CSVException e) {

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

		} catch (CSVException e) {

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

		} catch (CSVException e) {

		}
	}

	@Test
	public void givenIndianStateCodeCSVFileReturnsCorrectRecords() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			int numOfRecords = csvStateCensus.loadIndiaStateCodeData(INDIA_STATE_CODE_CSV_FILE_PATH);
			Assert.assertEquals(36, numOfRecords);

		} catch (CensusAnalyserException e) {
		} catch (IOException e) {

		} catch (CSVException e) {

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

		} catch (CSVException e) {

		}
	}

	@Test
	public void givenIndianStateCodeDataWithWrongFileTypeShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaStateCodeData(STATE_CODE_WRONG_TYPE_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
		} catch (IOException e) {

		} catch (CSVException e) {

		}
	}

	@Test
	public void givenIndianStateCodeDataWithWrongDelimiterShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaStateCodeData(STATE_CODE_WRONG_DELIMITER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_DELIMITER_TYPE);
		} catch (IOException e) {

		} catch (CSVException e) {

		}
	}

	@Test
	public void givenIndianStateCodeDataWithWrongHeaderShouldThrowException() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			csvStateCensus.loadIndiaStateCodeData(STATE_CODE_WRONG_HEADER_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(e.getExceptionType(), CensusAnalyserException.ExceptionType.WRONG_HEADER);
		} catch (IOException e) {
		} catch (CSVException e) {
		}
	}

	@Test
	public void givenIndianCensusDataWhenSortedShouldMatchResult() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			List<IndianCensusCSV> sortedList = csvStateCensus.sortAccordingToStateName(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals("Andaman and Nicobar Islands", sortedList.get(0).state);
		} catch (CSVException e) {
		}
	}

	@Test
	public void givenIndianStateCodeDataWhenSortedShouldMatchResult() {
		try {
			CSVStateCensus csvStateCensus = new CSVStateCensus();
			List<IndianStateCodeCSV> sortedList = csvStateCensus
					.sortAccordingToStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
			Assert.assertEquals("AN", sortedList.get(0).stateCode);
		} catch (CSVException e) {
		}
	}
}
