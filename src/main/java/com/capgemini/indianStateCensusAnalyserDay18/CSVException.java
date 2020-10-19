package com.capgemini.indianStateCensusAnalyserDay18;

public class CSVException extends Exception {

	enum ExceptionType {
		UNABLE_TO_PARSE, CSV_ERROR;
	}

	ExceptionType type;

	public CSVException(ExceptionType type) {
		this.type = type;
	}

	public CSVException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}
