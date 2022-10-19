package com.suomela.finaali.data;

public class Course {

    private String name;
    private String code;
    private String teacher;
    private String classroom;

    public Course() {

    }

    public Course(String name, String code, String teacher, String classroom) {
        this.name = name;
        this.code = code;
        this.teacher = teacher;
        this.classroom = classroom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
