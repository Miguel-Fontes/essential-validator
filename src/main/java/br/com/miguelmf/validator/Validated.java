package br.com.miguelmf.validator;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * A ValidatedEntity is a entity annotated with Javax Validation Constraints
 * and can be validated. This class provides common code to all those
 * entities, executing the {@code Validator} logic when requested by 
 * {@code Validated#validate()} method.
 * 
 * @author Miguel Fontes
 */
class ValidatedEntity {

    /**
     * Validate this Entity based on the present annotations. If Violations are detected,
     * thows a IllegalArgumentException with a String containing all errors concatenated
     * and separated by a comma. Example:
     * 
     *     A Person age should not be null, A Person age should be greater than zero
     * 
     * @throws IllegalArgumentException when Constraint Violantions are detected
     */
    protected void validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<ValidatedEntity>> constraintViolations = validator.validate(this);

        if (thereAreConstraintViolations(constraintViolations)) {
            throw new IllegalArgumentException(toMessageList(constraintViolations));
        }
    }

    private static boolean thereAreConstraintViolations(Set<ConstraintViolation<ValidatedEntity>> constraintViolations) {
        return !constraintViolations.isEmpty();
    }

    private static String toMessageList(Set<ConstraintViolation<ValidatedEntity>> constraintViolations) {
        return constraintViolations.stream()
            .map(violation -> violation.getMessage())
            .collect(Collectors.joining(", "));
    }

}