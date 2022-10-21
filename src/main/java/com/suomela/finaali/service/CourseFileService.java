package com.suomela.finaali.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suomela.finaali.data.Course;

import java.io.*;

@Service
public class CourseFileService {
    
    private File file = new File("src/main/java/com/suomela/finaali/service/saves/courses.json");

    final ObjectMapper mapper = new ObjectMapper();

    public void save(List<Course> courses) throws IOException {

        // if file doesnt exists then create new file
        if(!file.exists()) {
            try{
                file.createNewFile();
            }catch(IOException e){
                System.out.println("ERROR: Failed to create file" + e);
                return;
            }
        }

        // use object mapper to write courses list to the file defined at the start 
        mapper.writeValue(file, courses);
    }

    public List<Course> get() throws FileNotFoundException {

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

            // use the line var and object mapper to create courseList from the file

            List<Course> courseList = mapper.readValue(line, new TypeReference<List<Course>>(){});

            return courseList;

        }catch(Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}
