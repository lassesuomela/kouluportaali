package com.suomela.finaali.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.suomela.finaali.data.Student;

@Service
public class StudentService {
    
    private List<Student> students = new ArrayList<>();

    @Autowired
    private StudentFileService studentFileService;

    public void add(Student student) throws IOException {

        try{

            if(students != null && students.size() > 0){
    
                Student lastStudent = students.get(students.size() -1);
                student.setId(lastStudent.getId() + 1);
            }
            
            students.add(student);

            studentFileService.saveStudents(students);
        }catch(IOException e){
            throw e;
        }
    }

    public Student getById(long id) {
        for(Student student : students){
            if(student.getId() == id){
                return student;
            }
        }

        return null;
    }

    public List<Student> getAll() {
        return students;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fetchStudents() {
        try {
            students = studentFileService.getStudents();

        }catch(IOException e){
            System.out.println(e);
        }
    }
}
