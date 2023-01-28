package io.github.tahanima.data;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;
import lombok.ToString;

/**
 * This class provides common properties for all the test data.
 *
 * @author tahanima
 */
@Getter
@ToString
public class BaseData {
    @Parsed(field = "Test Case ID", defaultNullRead = "")
    private String testCaseId;

    @Parsed(field = "Test Case Description", defaultNullRead = "")
    private String testCaseDescription;
}
