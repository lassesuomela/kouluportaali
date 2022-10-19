package com.suomela.finaali.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suomela.finaali.data.Student;

@Service
public class StudentService {
    
    private List<Student> students = new ArrayList<>();

    @Autowired
    StudentFileService studentFileService;

    public void add(Student student) throws IOException {

        try{
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

    public void fetchStudents() {
        try {
            students = studentFileService.getStudents();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
