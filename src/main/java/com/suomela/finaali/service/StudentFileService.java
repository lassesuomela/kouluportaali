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

        // if file doesnt exists then create new file
        if(!file.exists()) {
            file.createNewFile();
        }

        // use object mapper to write student list to the file defined at the start 
        mapper.writeValue(file, students);
    }

    public List<Student> getStudents() throws FileNotFoundException {

        // if file doesnt exists then return empty list
        if(!file.exists()) {
            return new ArrayList<>();
        }

        try{
            // use scanner to read the file and add the text to the line var
            Scanner scanner = new Scanner(file);

            String line = "";

            while (scanner.hasNext()){
                line += scanner.nextLine();
            }

            scanner.close();

            // use the line var and object mapper to create studentlist from the file
            List<Student> studentList = mapper.readValue(line, new TypeReference<List<Student>>(){});

            return studentList;

        }catch(Exception e){
            System.out.println(e);
        }

        return new ArrayList<>();
    }
}
