package io.github.tahanima.data;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;

/**
 * This class provides common properties
 * for all the test data.
 *
 * @author tahanima
 * @since 01/29/2022
 */
public class BaseData {
    @Getter
    @Parsed(field = "Test Case ID", defaultNullRead = "")
    private String testCaseId;

    @Getter
    @Parsed(field = "Test Case Description", defaultNullRead = "")
    private String testCaseDescription;

    @Override
    public String toString() {
        return String.format("testCaseId=%s, testCaseDescription=%s",
                testCaseId,
                testCaseDescription);
    }
}
