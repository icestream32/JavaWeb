package com.servlet.javaweb.bean;

import java.util.Objects;

public class Student {
    private String id;

    private String job;

    public Student() {
    }

    public Student(String id, String job) {
        this.id = id;
        this.job = job;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(job, student.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, job);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
