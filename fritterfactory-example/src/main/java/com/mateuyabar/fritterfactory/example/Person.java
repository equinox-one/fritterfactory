package com.mateuyabar.fritterfactory.example;


public class Person {
    String name;
    String surname;
    int age;
    Adress adress;
    String description;
    Category category;

    String image;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", adress=" + adress +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", image='" + image + '\'' +
                '}';
    }
}
