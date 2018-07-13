package br.com.miguelfontes.validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Person extends ValidatedEntity {

    public static final String NAME_SHOULD_NOT_BE_NULL_OR_BLANK = "A Person name should not be null or blank";
    public static final String AGE_SHOULD_NOT_BE_NULL = "A Person age should not be null";
    public static final String AGE_SHOULD_BE_GT_ZERO = "A Person age should be greater than zero";

    @NotBlank(message = NAME_SHOULD_NOT_BE_NULL_OR_BLANK)
    private final String name;

    @NotNull(message = AGE_SHOULD_NOT_BE_NULL)
    @Min(value = 1, message = AGE_SHOULD_BE_GT_ZERO)
    private final Integer age;

    private Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        validate();
    }

    public static Person of(String name, Integer age) {
        Person person = new Person(name, age);

        return person;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Person { name: \"%s\", age: %d", name, age);
    }

}