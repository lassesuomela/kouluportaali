package com.suomela.finaali.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.suomela.finaali.data.Course;

@Service
public class CourseService {
    
    private List<Course> courses = new ArrayList<>();

    @Autowired
    private CourseFileService courseFileService;

    public void add(Course course) throws IOException {

        // try to add course object to courses list and save courses to file
        try{
            courses.add(course);

            courseFileService.save(courses);
        }catch(IOException e){
            throw e;
        }
    }

    public List<Course> getAll() {
        return courses;
    }

    public Course getCourseByCode(String code){
        // loop through courses and check if there is a match for the code
        // providded, if there is return that object else return null

        for (Course course : courses) {

            if(course.getCode().equals(code)){
                return course;
            }
        }

        return null;
    }

    // at the start of the program fetch all courses from the file and
    // assign them to courses list
    @EventListener(ApplicationReadyEvent.class)
    public void fetchCourses() {
        try {
            courses = courseFileService.get();

        }catch(IOException e){
            System.out.println(e);
        }
    }
}
