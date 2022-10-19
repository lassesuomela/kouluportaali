package com.suomela.finaali.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suomela.finaali.data.Student;

import java.io.*;

@Service
public class StudentFileService {
    
    private File file = new File("src/main/java/com/suomela/finaali/service/saves/students.json");

    final ObjectMapper mapper = new ObjectMapper();

    public void saveStudents(List<Student> students) throws IOException {

        if(!file.exists()) {
            file.createNewFile();
        }

        mapper.writeValue(file, students);
    }

    public List<Student> getStudents() throws FileNotFoundException {

        if(!file.exists()) {
            return null;
        }

        try{
            Scanner scanner = new Scanner(file);

            String line = "";

            while (scanner.hasNext()){
                line += scanner.nextLine();
            }

            scanner.close();

            List<Student> studentList = mapper.readValue(line, new TypeReference<List<Student>>(){});

            return studentList;

        }catch(Exception e){
            System.out.println(e);
        }

        return new ArrayList<>();
    }
}
