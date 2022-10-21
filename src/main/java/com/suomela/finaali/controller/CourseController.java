package com.suomela.finaali.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suomela.finaali.data.Course;
import com.suomela.finaali.service.CourseService;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("courses")
    public List<Course> getCourseList() {
        return courseService.getAll();
    }

    @PostMapping("course")
    public boolean addCourse(@ModelAttribute Course course) throws IOException {

        if(course.getCode().isEmpty() || course.getName().isEmpty() || course.getTeacher().isEmpty() || course.getClassroom().isEmpty()) {
            return false;
        }

        try {
            courseService.add(course);
            return true;
        } catch (IOException e){
            return false;
        }
    }
}
