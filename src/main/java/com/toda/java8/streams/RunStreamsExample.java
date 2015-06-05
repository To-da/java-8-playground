package com.toda.java8.streams;

import static java.util.Arrays.asList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class RunStreamsExample {

    public static void main(String[] args) {
        final Friend friendStanda = Friend.createFriend(new Person.PersonBuilder().setName(new Person.Name("Standa Řezáč")), LocalDate.now());

        final Friend friendKarel = Friend.createFriend(new Person.PersonBuilder().setName(new Person.Name("Karel Izan")), LocalDate.now());


        final Friend friendOfStandaAndKarel = Friend.createFriend(
                new Person.PersonBuilder()
                        .setName(new Person.Name("Pavel Gredl"))
                        .setFriends(new HashSet<>(asList(friendStanda, friendKarel))),
                LocalDate.now());

        final Friend friendOffriendOfStandaAndKarel = Friend.createFriend(
                new Person.PersonBuilder()
                        .setName(new Person.Name("Radim  Novák"))
                        .setFriends(new HashSet<>(asList(friendOfStandaAndKarel))),
                LocalDate.now());


        System.out.println("Should not have friend of friends:");
        System.out.println(friendStanda.getPerson().friendOfFriends());

        System.out.println("Should have friend Pavel and Standa");
        System.out.println(friendKarel.getPerson().friendOfFriends());

        System.out.println("Should have friend of friends:");
        System.out.println(friendOfStandaAndKarel.getPerson().friendOfFriends());

        System.out.println("Should have friend of friends of Standa a Karel:");
        System.out.println(friendOffriendOfStandaAndKarel.getPerson().friendOfFriends());

        //REDUCE
        final Integer integer = Arrays.asList(1, 2, 6, 3).stream().reduce((x, y) -> x + y).get();
        System.out.println(integer);


        //LIST contains element from LIST
        final List<String> stringList1 = Arrays.asList("1", "123", "A", "X");
        final List<String> stringList2 = Arrays.asList("U", "32", "Q", "X", "1FW");

        System.out.println(stringList2.stream().anyMatch(stringList1::contains));
    }

}
