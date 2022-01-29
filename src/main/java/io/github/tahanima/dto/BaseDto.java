package io.github.tahanima.dto;

import com.univocity.parsers.annotations.Parsed;

/**
 * @author tahanima
 * @since 01/29/2022
 */
public class BaseDto {
    @Parsed(field = "Test Case ID")
    private String testCaseId;

    @Parsed(field = "Test Case Description")
    private String testCaseDescription;

    public String getTestCaseId() {
        return testCaseId;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    @Override
    public String toString() {
        return "testCaseId=" + testCaseId
                + ", testCaseDescription=" + testCaseDescription;
    }
}
