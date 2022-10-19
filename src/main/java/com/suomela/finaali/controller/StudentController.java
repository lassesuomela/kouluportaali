package com.suomela.finaali.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suomela.finaali.data.Student;
import com.suomela.finaali.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("students")
    public List<Student> getStudentList() {
        return studentService.getAll();
    }

    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.getById(id);
    }

    @PostMapping("students")
    public Student addStudent(@ModelAttribute Student student) throws IOException {

        studentService.add(student);
        return student;
    }

    @GetMapping("students/fetch")
    public void fetchStudentList() {
        studentService.fetchStudents();
    }

}
