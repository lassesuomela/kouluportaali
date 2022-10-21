package com.suomela.finaali.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        // check if some values are missing before proceeding
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

        // get student object by using student id from the user
        Student currentStudent = studentService.getById(student);

        if(currentStudent == null){
            return "Error: Student is null";
        }

        // get course object by using course code from the user
        Course newCourse = courseService.getCourseByCode(course);

        if(newCourse == null){
            return "Error: Course is null";
        }


        // loop through students courses and check if previously fetched course matches one of them
        // if there is a match then return error msg
        for(Course c : currentStudent.getCourses()){
            if(c.getName().equals(newCourse.getName())){
                return "Error: Duplicate course";
            }
        }

        try {
            studentService.addCourse(currentStudent, newCourse);
            return "Success";

        }catch (IOException e){
            return "Error: " + e;
        }
    }
}
