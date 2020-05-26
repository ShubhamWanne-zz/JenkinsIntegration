package com.securonix.MockitoTesting.model;

public class Employee {
    private String id;
    private String name;
    private String age;

    public Employee(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
