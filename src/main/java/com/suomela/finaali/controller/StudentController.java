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

    @GetMapping("student/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.getById(id);
    }

    @PostMapping("student")
    public boolean addStudent(@ModelAttribute Student student) throws IOException {

        if(student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getDob().isEmpty()) {
            return false;
        }

        try {
            studentService.add(student);
            return true;
        } catch (IOException e){
            return false;
        }
    }
}
