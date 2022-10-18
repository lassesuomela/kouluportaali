package com.suomela.finaali.data;

import java.util.List;

public class Course {

    private String name;
    private String teacher;
    private String classroom;
    private List<Student> students;

    public Course() {

    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassroom() {
        return this.classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getInfo() {
        return this.name + " " + this.teacher + " " + this.classroom + " " + this.students;
    }
    
}
