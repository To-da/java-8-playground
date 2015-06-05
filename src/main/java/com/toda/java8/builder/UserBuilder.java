package com.toda.java8.builder;

import java.util.function.Consumer;

public class UserBuilder {

    private int age;
    private String firstname;
    private String surname;
    private String address;

    private UserBuilder() {
    }

    public UserBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    private User build() {
        return new UserBuilderGenerated().setAge(age).setFirstname(firstname).setSurname(surname).setAddress(address).createUser();
    }

    public static User build(final Consumer<UserBuilder> userBuilderConsumer) {
        final UserBuilder userBuilder = new UserBuilder();
        userBuilderConsumer.accept(userBuilder);
        return userBuilder.build();
    }
}
