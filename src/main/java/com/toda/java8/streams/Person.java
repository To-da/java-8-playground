package com.toda.java8.streams;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Person {

    enum Gender {MALE, FEMALE};

    private final Name name;

    private final LocalDate birthDay;

    private final Gender gender;

    private final Hometown hometown;

    private final Set<Friend> friends = new HashSet<>();

    private Person(Name name, LocalDate birthDay, Gender gender, Hometown hometow, Set<Friend> friends) {
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.hometown = hometow;
        if (friends != null) {
            this.friends.addAll(friends);
        }
    }

    public Name getName() {
        return name;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Gender getGender() {
        return gender;
    }

    public Hometown getHometown() {
        return hometown;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public Set<Person> friendOfFriends() {
        return friends.stream()
                .flatMap(friend -> friend.getPerson().getFriends().stream())
                .map(Friend::getPerson)
                .filter(person -> person != this)
                .collect(Collectors.toSet());
    }

    public PersonBuilder getBuilder() {
        return new PersonBuilder();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", birthDay=" + birthDay +
                ", gender=" + gender +
                ", hometown=" + hometown +
                ", friends=" + friends +
                '}';
    }

    public static class Name  {

        private String firstname;

        private String lastname;

        public Name(String name) {
            final String[] split = name.split(" ", 2);
            firstname = split[0];
            lastname = split[1];
        }

        @Override
        public String toString() {
            return "Name{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    '}';
        }
    }

    public static class Hometown  {

        private String name;

        private String state;

        public Hometown(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Hometown{" +
                    "name='" + name + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }
    }

    public static class PersonBuilder {
        private Person.Name name;
        private LocalDate birthDay;
        private Person.Gender gender;
        private Person.Hometown hometow;
        private Set<Friend> friends;

        public PersonBuilder setName(Person.Name name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setBirthDay(LocalDate birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public PersonBuilder setGender(Person.Gender gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder setHometow(Person.Hometown hometow) {
            this.hometow = hometow;
            return this;
        }

        public PersonBuilder setFriends(Set<Friend> friends) {
            this.friends = friends;
            return this;
        }

        public Person createPerson() {
            return new Person(name, birthDay, gender, hometow, friends);
        }
    }
}


