package io.github.tahanima.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;

import io.github.tahanima.dto.BaseDto;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tahanima
 */
@Slf4j
public final class DataProviderUtil {

    private DataProviderUtil() {}

    public static Object[][] processTestData(
            Class<? extends BaseDto> clazz, String fileName, String id) {
        if (fileName.endsWith(".csv")) return processCsv(clazz, fileName, id);

        if (fileName.endsWith(".json")) return processJson(clazz, fileName, id);

        return new Object[0][0];
    }

    private static Object[][] processCsv(
            Class<? extends BaseDto> clazz, String fileName, String id) {
        var settings = new CsvParserSettings();

        settings.getFormat().setLineSeparator("\n");

        var routines = new CsvRoutines(settings);

        try (var reader =
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
            ArrayList<ArrayList<? extends BaseDto>> testData = new ArrayList<>();

            routines.iterate(clazz, reader)
                    .forEach(
                            e -> {
                                if (e.getTestCaseId().equals(id)) {
                                    testData.add(
                                            new ArrayList<>() {
                                                {
                                                    add(e);
                                                }
                                            });
                                }
                            });

            return toArray(testData);
        } catch (IOException e) {
            log.error("DataProviderUtil::processCsv", e);
        }

        return new Object[0][0];
    }

    private static <T extends BaseDto> Object[][] processJson(
            Class<T> clazz, String fileName, String id) {
        try (var reader =
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
            ArrayList<ArrayList<? extends BaseDto>> testData = new ArrayList<>();
            List<T> jsonData =
                    new Gson()
                            .fromJson(
                                    reader,
                                    TypeToken.getParameterized(List.class, clazz).getType());

            jsonData.forEach(
                    e -> {
                        if (e.getTestCaseId().equals(id)) {
                            testData.add(
                                    new ArrayList<>() {
                                        {
                                            add(e);
                                        }
                                    });
                        }
                    });

            return toArray(testData);
        } catch (IOException e) {
            log.error("DataProviderUtil::processJson", e);
        }
        return new Object[0][0];
    }

    private static Object[][] toArray(ArrayList<ArrayList<? extends BaseDto>> testData) {
        int noOfRows = testData.size();
        Object[][] testDataArray = new Object[noOfRows][1];

        for (int i = 0; i < noOfRows; i++) {
            testDataArray[i][0] = testData.get(i).get(0);
        }

        return testDataArray;
    }
}
