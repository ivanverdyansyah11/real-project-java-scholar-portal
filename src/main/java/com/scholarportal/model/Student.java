package com.scholarportal.model;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String username;
    private String password;
    private double gpa;
    private String subject;
    private Date enrollmentDate;

    public Student() {
    }

    public Student(int id, String name, String username, String password, double gpa, String subject, Date enrollmentDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.gpa = gpa;
        this.subject = subject;
        this.enrollmentDate = enrollmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}