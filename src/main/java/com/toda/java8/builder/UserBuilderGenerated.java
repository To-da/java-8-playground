package com.toda.java8.builder;

public class UserBuilderGenerated {
    private int age;
    private String firstname;
    private String surname;
    private String address;

    public UserBuilderGenerated setAge(int age) {
        this.age = age;
        return this;
    }

    public UserBuilderGenerated setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilderGenerated setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilderGenerated setAddress(String address) {
        this.address = address;
        return this;
    }

    public User createUser() {
        return new User(age, firstname, surname, address);
    }

}