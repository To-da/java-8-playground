package com.toda.java8.builder;

public class RunBuilderExample {

    public static void main(String[] args) {
        User user = UserBuilder.build(builder -> builder.setFirstname("Kirk").setAge(54));
        System.out.println(user);
    }

}
