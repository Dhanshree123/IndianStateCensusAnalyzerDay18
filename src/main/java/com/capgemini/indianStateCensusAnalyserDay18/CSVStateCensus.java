package com.capgemini.indianStateCensusAnalyserDay18;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.indianStateCensusAnalyserDay18.CensusAnalyserException.ExceptionType;
import com.capgemini.openCSVBuilder.CSVBuilderFactory;
import com.capgemini.openCSVBuilder.CSVException;
import com.capgemini.openCSVBuilder.ICSVBuilder;
import com.google.gson.Gson;

public class CSVStateCensus {

	public static final String JSON_FILE_SORT_STATE = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\SortedStateName.json";
	public static final String JSON_FILE_SORT_STATE_CODE = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\SortedStateCode.json";
	public static final String JSON_FILE_SORT_STATE_POPULATION = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\SortedStatePopulation.json";
	public static final String JSON_FILE_SORT_STATE_POPULATION_DENSITY = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\SortedStatePopulationDensity.json";
	public static final String JSON_FILE_SORT_STATE_AREA = "C:\\Users\\Admin\\eclipse-workspace\\IndianStateCensusAnalyserDay18\\SortedStateArea.json";

	public List<IndianCensusCSV> indianCensusCSVList;
	public List<IndianStateCodeCSV> indianStateCodeCSVList;

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, IOException, CSVException {
		String[] csvFile = csvFilePath.split("[.]");
		if (!csvFile[1].equals("csv")) {
			throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
		}

		checkDelimiter(csvFilePath);
		checkHeader(csvFilePath);
		try {
			Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			indianCensusCSVList = csvBuilder.getCsvFileList(reader, IndianCensusCSV.class);
			return indianCensusCSVList.size();
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
		}
	}

	public void checkDelimiter(String csvFilePath) throws CensusAnalyserException {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineComponents = line.split(",");
				if (lineComponents.length != 4)
					throw new CensusAnalyserException("This file is having incorrect delimiter",
							ExceptionType.WRONG_DELIMITER_TYPE);
			}
		} catch (IOException e) {
			throw new CensusAnalyserException("This file path is incorrect", ExceptionType.WRONG_FILE_PATH);
		}

	}

	public void checkHeader(String csvFilePath) throws CensusAnalyserException {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath));
			String FirstLine = br.readLine();
			String[] columns = FirstLine.split(",");
			boolean isCorrect = columns[0].equals("State") && columns[1].equals("Population")
					&& columns[2].equals("AreaInSqKm") && columns[3].equals("DensityPerSqKm");
			if (!isCorrect) {
				throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.WRONG_HEADER);
			}

		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (NullPointerException | IOException e) {
		}

	}

	public int loadIndiaStateCodeData(String csvFilePath) throws CensusAnalyserException, IOException, CSVException {
		String[] csvFile = csvFilePath.split("[.]");
		if (!csvFile[1].equals("csv")) {
			throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
		}

		checkDelimiterStateCode(csvFilePath);
		checkHeaderStateCode(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			indianStateCodeCSVList = csvBuilder.getCsvFileList(reader, IndianStateCodeCSV.class);
			for (IndianStateCodeCSV c : indianStateCodeCSVList) {
				System.out.println(c.stateCode);
			}
			return indianStateCodeCSVList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		}
	}

	public void checkDelimiterStateCode(String csvFilePath) throws CensusAnalyserException {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath));
			while (true) {
				String line = br.readLine();
				String[] Linecolumns = line.split(",");
				if (Linecolumns.length < 2) {
					throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.WRONG_DELIMITER_TYPE);
				}
			}
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (NullPointerException | IOException e) {
		}

	}

	public void checkHeaderStateCode(String csvFilePath) throws CensusAnalyserException {
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath));
			String FirstLine = br.readLine();
			String[] columns = FirstLine.split(",");
			boolean isCorrect = columns[0].equals("State") && columns[1].equals("StateCode");
			if (!isCorrect) {
				throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.WRONG_HEADER);
			}

		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (NullPointerException | IOException e) {
		}

	}

	public List<IndianCensusCSV> sortAccordingToStateName(String csvFilePath) throws CSVException {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			indianCensusCSVList = csvBuilder.getCsvFileList(reader, IndianCensusCSV.class);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<IndianCensusCSV> sortedList = indianCensusCSVList.stream()
				.sorted((element1, element2) -> element1.state.compareTo(element2.state)).collect(Collectors.toList());
		Gson gson = new Gson();
		String json = gson.toJson(sortedList);
		FileWriter writer;

		try {
			writer = new FileWriter(JSON_FILE_SORT_STATE);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedList;
	}

	public List<IndianStateCodeCSV> sortAccordingToStateCode(String csvFilePath) throws CSVException {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			indianStateCodeCSVList = csvBuilder.getCsvFileList(reader, IndianStateCodeCSV.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<IndianStateCodeCSV> sortedList = indianStateCodeCSVList.stream()
				.sorted((element1, element2) -> element1.stateCode.compareTo(element2.stateCode))
				.collect(Collectors.toList());
		Gson gson = new Gson();
		String json = gson.toJson(sortedList);
		FileWriter writer;

		try {
			writer = new FileWriter(JSON_FILE_SORT_STATE_CODE);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedList;
	}

	public List<IndianCensusCSV> sortAccordingToStatePopulation(String csvFilePath) throws CSVException {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			indianCensusCSVList = csvBuilder.getCsvFileList(reader, IndianCensusCSV.class);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<IndianCensusCSV> sortedList = indianCensusCSVList.stream()
				.sorted((p1, p2) -> Integer.compare(p1.population, p2.population)).collect(Collectors.toList());
		Collections.reverse(sortedList);
		Gson gson = new Gson();
		String json = gson.toJson(sortedList);
		FileWriter writer;

		try {
			writer = new FileWriter(JSON_FILE_SORT_STATE_POPULATION);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedList;
	}

	public List<IndianCensusCSV> sortAccordingToStatePopulationDensity(String csvFilePath) throws CSVException {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			indianCensusCSVList = csvBuilder.getCsvFileList(reader, IndianCensusCSV.class);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<IndianCensusCSV> sortedList = indianCensusCSVList.stream()
				.sorted((p1, p2) -> Integer.compare(p1.densityPerSqKm, p2.densityPerSqKm)).collect(Collectors.toList());
		Collections.reverse(sortedList);
		Gson gson = new Gson();
		String json = gson.toJson(sortedList);
		FileWriter writer;

		try {
			writer = new FileWriter(JSON_FILE_SORT_STATE_POPULATION_DENSITY);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedList;
	}

	public List<IndianCensusCSV> sortAccordingToStateArea(String csvFilePath) throws CSVException {
		Reader reader = null;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			indianCensusCSVList = csvBuilder.getCsvFileList(reader, IndianCensusCSV.class);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<IndianCensusCSV> sortedList = indianCensusCSVList.stream()
				.sorted((p1, p2) -> Integer.compare(p1.areaInSqKm, p2.areaInSqKm)).collect(Collectors.toList());
		Collections.reverse(sortedList);
		Gson gson = new Gson();
		String json = gson.toJson(sortedList);
		FileWriter writer;

		try {
			writer = new FileWriter(JSON_FILE_SORT_STATE_AREA);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedList;
	}

}
