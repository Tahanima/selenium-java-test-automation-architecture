package io.github.tahanima.data;

import com.univocity.parsers.annotations.Parsed;

/**
 * This class provides common properties
 * for all the test data.
 *
 * @author tahanima
 * @since 01/29/2022
 */
public class BaseData {
    @Parsed(field = "Test Case ID", defaultNullRead = "")
    private String testCaseId;

    @Parsed(field = "Test Case Description", defaultNullRead = "")
    private String testCaseDescription;

    public String getTestCaseId() {
        return testCaseId;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    @Override
    public String toString() {
        return String.format("testCaseId=%s, testCaseDescription=%s",
                testCaseId,
                testCaseDescription);
    }
}
