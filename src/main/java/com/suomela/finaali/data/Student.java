package com.suomela.finaali.data;

public class Student {

    private String firstName;
    private String lastName;
    private int age;
    private long id;
    private static long counter = 0;

    public Student () {

    }
    
    public Student(String firstName, String lastName, int age) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
