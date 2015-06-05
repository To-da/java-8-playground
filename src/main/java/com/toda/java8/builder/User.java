package com.toda.java8.builder;

public class User {

    private final int age;

    private final String firstname;

    private final String surname;

    private final String address;

    User(int age, String firstname, String surname, String address) {
        this.age = age;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
