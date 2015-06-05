package com.toda.java8.streams;

import java.time.LocalDate;
import java.util.Random;

public class Friend {

    private Person person;

    private LocalDate becameFriends;

    private Friend(Person person, LocalDate becameFriends) {
        this.person = person;
        this.becameFriends = becameFriends;
    }

    public static Friend createFriend(Person.PersonBuilder personBuilder, LocalDate becameFriends) {
        personBuilder.setBirthDay(LocalDate.now().minusMonths(new Random().nextInt()).minusDays(new Random().nextInt()));
        return new Friend(personBuilder.createPerson(), becameFriends);
    }

    public Person getPerson() {
        return person;
    }

    public LocalDate getBecameFriends() {
        return becameFriends;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "person=" + person +
                ", becameFriends=" + becameFriends +
                '}';
    }
}
