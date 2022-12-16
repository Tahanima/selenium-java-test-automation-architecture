package io.github.tahanima.util;

import static io.github.tahanima.config.ConfigurationManager.configuration;

import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import io.github.tahanima.data.BaseData;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author tahanima
 */
public final class DataProviderUtil {
  private DataProviderUtil() {}

  private static Object[][] convert(final ArrayList<ArrayList<? extends BaseData>> data) {
    int noOfRows = data.size();
    Object[][] dataArray = new Object[noOfRows][1];

    for (int i = 0; i < noOfRows; i++) {
      dataArray[i][0] = data.get(i).get(0);
    }

    return dataArray;
  }

  public static Object[][] processCsv(
      final Class<? extends BaseData> clazz, String csvFilePath, final String testCaseId) {
    CsvParserSettings parserSettings = new CsvParserSettings();
    parserSettings.getFormat().setLineSeparator("\n");
    CsvRoutines routines = new CsvRoutines(parserSettings);
    csvFilePath = configuration().baseTestDataPath() + csvFilePath;

    try (Reader inputReader =
        new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8)) {
      ArrayList<ArrayList<? extends BaseData>> parsedData = new ArrayList<>();

      for (BaseData baseData : routines.iterate(clazz, inputReader)) {
        if (baseData.getTestCaseId().equals(testCaseId)) {
          parsedData.add(
              new ArrayList<>() {
                {
                  add(baseData);
                }
              });
        }
      }

      return convert(parsedData);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new Object[0][0];
  }
}
