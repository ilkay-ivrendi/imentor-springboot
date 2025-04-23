package com.imentor.student;

import java.time.LocalDate;

public class Student {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private Integer age;
    private LocalDate date_of_birth;

    public Student() {

    }

    public Student(Long id, String first_name, String last_name, String email, Integer age, LocalDate date_of_birth) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
        this.date_of_birth = date_of_birth;
    }

    public Student(String first_name, String last_name, String email, Integer age, LocalDate date_of_birth) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
        this.date_of_birth = date_of_birth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
                + ", age=" + age + ", date_of_birth=" + date_of_birth + "]";
    }
}
