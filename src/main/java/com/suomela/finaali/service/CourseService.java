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

    @EventListener(ApplicationReadyEvent.class)
    public void fetchCourses() {
        try {
            courses = courseFileService.get();

        }catch(IOException e){
            System.out.println(e);
        }
    }
}
