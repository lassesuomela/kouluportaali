package com.suomela.finaali.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.suomela.finaali.data.Course;
import com.suomela.finaali.data.Student;

@Service
public class StudentService {
    
    private List<Student> students = new ArrayList<>();

    @Autowired
    private StudentFileService studentFileService;

    public void add(Student student) throws IOException {

        // try adding student object to students list
        try{

            // check if students object exists and it has some content in it
            if(students != null && students.size() > 0){
    
                // fetch last student from the list and get that id and increment it
                // and assing new id to the current student object
                Student lastStudent = students.get(students.size() -1);
                student.setId(lastStudent.getId() + 1);
            }
            

            // add student object to the list
            students.add(student);

            // and save them to json file
            studentFileService.saveStudents(students);
        }catch(IOException e){
            throw e;
        }
    }

    public Student getById(long id) {

        // loop through students and if there is a id match then return that student
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }

        return null;
    }

    public void addCourse(Student student, Course course) throws IOException {

        // get the index of the student that we are trying to add a course into
        int index = students.indexOf(student);

        // get list of courses from the student
        List<Course> currentCourses = student.getCourses();

        // add course to the course list
        currentCourses.add(course);

        // get student object from the list using the indedx and set that objects
        // courses to currentCourses list
        students.get(index).setCourses(currentCourses);

        // save students to file
        studentFileService.saveStudents(students);
    }

    public List<Student> getAll() {
        return students;
    }

    // fetch students from file at the start of the program
    // assign those students to students list
    @EventListener(ApplicationReadyEvent.class)
    public void fetchStudents() {
        try {
            students = studentFileService.getStudents();

        }catch(IOException e){
            System.out.println(e);
        }
    }
}
