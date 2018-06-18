package br.com.miguelmf.validator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.util.Arrays.asList;import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

@DisplayName("Person")
class PersonTest {

    @Test
    @DisplayName("Person should throw IllegalArgumentException when built with invalid attributes")
    void shouldReturnValidationError(TestReporter reporter) {
        final String aPersonNameShouldNotBeNullOrBlank = "A Person name should not be null or blank";
        final String aPersonAgeShouldNotBeNull = "A Person age should not be null";
        final String aPersonAgeShouldBeGreaterThanZero = "A Person age should be greater than zero";

        assertAll(
            () -> assertSingleValidationError("", 18, aPersonNameShouldNotBeNullOrBlank, "Blank Name", reporter),
            () -> assertSingleValidationError(null, 18, aPersonNameShouldNotBeNullOrBlank, "Null Name", reporter),
            () -> assertSingleValidationError("Name", null, aPersonAgeShouldNotBeNull, "Null Age", reporter),
            () -> assertSingleValidationError("Name", 0, aPersonAgeShouldBeGreaterThanZero, "Zero Age", reporter)
        );

        assertAll(
            () -> assertMultipleValidationErrors("", 0,
                asList(aPersonAgeShouldBeGreaterThanZero, aPersonNameShouldNotBeNullOrBlank),
                "Zero Age and Blank Name", reporter)
        );
    }

    private void assertSingleValidationError(String name, Integer age, String expected, String entryKey,
            TestReporter reporter) {
        assertValidationError(name, age, entryKey, reporter, exception -> 
            assertEquals(expected, exception.getMessage()));
    }

    private void assertMultipleValidationErrors(String name, Integer age, List<String> expectedErrors, String entryKey,
            TestReporter reporter) {
        assertValidationError(name, age, entryKey, reporter, exception -> 
            expectedErrors.forEach(error -> assertTrue(exception.getMessage().contains(error))));
    }

    private void assertValidationError(String name, Integer age, String entryKey,
    TestReporter reporter, Consumer<Throwable> consumer) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Person person = Person.of(name, age);
            reporter.publishEntry(entryKey, person.toString());
        });

        consumer.accept(exception);
    }

    @Test
    @DisplayName("should build a valid Person")
    void shouldBuildValidPerson(TestReporter reporter) {
        final String name = "Miguel Fontes";
        final Integer age = 30;

        final Person person = Person.of(name, age);

        assertAll(
            () -> assertNotNull(person.getName()), 
            () -> assertEquals(name, person.getName()),
            () -> assertNotNull(person.getAge()), 
            () -> assertEquals(age, person.getAge())
        );

        reporter.publishEntry("Built person", person.toString());
    }

}