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

@DisplayName("ValidatedEntityTest")
class ValidatedEntityTest {

    @Test
    @DisplayName("Person should throw IllegalArgumentException when built with invalid attributes")
    void shouldReturnValidationError(TestReporter reporter) {
        assertAll(
            () -> assertSingleValidationError("", 18, Person.NAME_SHOULD_NOT_BE_NULL_OR_BLANK, "Blank Name", reporter),
            () -> assertSingleValidationError(null, 18, Person.NAME_SHOULD_NOT_BE_NULL_OR_BLANK, "Null Name", reporter),
            () -> assertSingleValidationError("Name", null, Person.AGE_SHOULD_NOT_BE_NULL, "Null Age", reporter),
            () -> assertSingleValidationError("Name", 0, Person.AGE_SHOULD_BE_GT_ZERO, "Zero Age", reporter)
        );

        assertAll(
            () -> assertMultipleValidationErrors("", 0,
                asList(Person.AGE_SHOULD_BE_GT_ZERO, Person.NAME_SHOULD_NOT_BE_NULL_OR_BLANK),
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
    @DisplayName("should build a valid Entity")
    void shouldBuildAValidEntity(TestReporter reporter) {
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