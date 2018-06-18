package br.com.miguelmf.validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Person extends ValidatedEntity {

    @NotBlank(message="A Person name should not be null or blank")
    private final String name;

    @NotNull(message="A Person age should not be null")
    @Min(value=1, message="A Person age should be greater than zero")
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