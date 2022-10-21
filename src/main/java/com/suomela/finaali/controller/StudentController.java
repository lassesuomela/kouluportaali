package com.suomela.finaali.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suomela.finaali.data.Course;
import com.suomela.finaali.data.Student;
import com.suomela.finaali.service.CourseService;
import com.suomela.finaali.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

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

    @PostMapping("student/enroll")
    public String enrollStudent(@RequestParam long student, @RequestParam String course) {

        Student currentStudent = studentService.getById(student);

        if(currentStudent == null){
            return "Error current student is null";
        }

        System.out.println("Cheking with code =" + course);
        Course newCourse = courseService.getCourseByCode(course);

        if(newCourse == null){
            return "Error current course is null";
        }

        try {
            studentService.addCourse(currentStudent, newCourse);
            return "Done";

        }catch (IOException e){
            return "Error occured " + e;
        }
    }
}
