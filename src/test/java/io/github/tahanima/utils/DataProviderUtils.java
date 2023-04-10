package io.github.tahanima.utils;

import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;

import io.github.tahanima.data.BaseTestData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author tahanima
 */
public final class DataProviderUtils {
    private DataProviderUtils() {}

    private static Object[][] convertToArray(ArrayList<ArrayList<? extends BaseTestData>> data) {
        int noOfRows = data.size();
        Object[][] dataArray = new Object[noOfRows][1];

        for (int i = 0; i < noOfRows; i++) {
            dataArray[i][0] = data.get(i).get(0);
        }

        return dataArray;
    }

    public static Object[][] processCsv(
            Class<? extends BaseTestData> clazz, String csvFilePath, String testCaseId) {
        CsvParserSettings parserSettings = new CsvParserSettings();

        parserSettings.getFormat().setLineSeparator("\n");

        CsvRoutines routines = new CsvRoutines(parserSettings);

        try (Reader inputReader =
                     new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8)) {
            ArrayList<ArrayList<? extends BaseTestData>> testData = new ArrayList<>();

            for (BaseTestData data : routines.iterate(clazz, inputReader)) {
                if (data.getTestCaseId().equals(testCaseId)) {
                    testData.add(
                            new ArrayList<>() {
                                {
                                    add(data);
                                }
                            });
                }
            }

            return convertToArray(testData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Object[0][0];
    }
}
