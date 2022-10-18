package com.suomela.finaali.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.suomela.finaali.data.Student;

@Service
public class StudentService {
    
    private List<Student> students = new ArrayList<>();

    public void add(Student student) {
        students.add(student);
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
}
