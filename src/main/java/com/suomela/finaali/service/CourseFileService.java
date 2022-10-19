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

        if(!file.exists()) {
            file.createNewFile();
        }

        mapper.writeValue(file, courses);
    }

    public List<Course> get() throws FileNotFoundException {

        if(!file.exists()) {
            return new ArrayList<>();
        }

        try{
            Scanner scanner = new Scanner(file);

            String line = "";

            while (scanner.hasNext()){
                line += scanner.nextLine();
            }

            scanner.close();

            List<Course> courseList = mapper.readValue(line, new TypeReference<List<Course>>(){});

            return courseList;

        }catch(Exception e){
            System.out.println(e);
        }

        return new ArrayList<>();
    }
}
