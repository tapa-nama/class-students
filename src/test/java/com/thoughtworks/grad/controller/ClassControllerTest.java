package com.thoughtworks.grad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.domain.Student;
import com.thoughtworks.grad.repository.ClassStorage;
import com.thoughtworks.grad.repository.StudentStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClassControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void should_find_all_the_classes() throws Exception {
        ClassStorage.save(new ClassRoom(1, "class1"));
        ClassStorage.save(new ClassRoom(2, "class2"));

        mockMvc.perform(get("/api/classes")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].className").value("class1"))
                .andExpect(jsonPath("$[1].className").value("class2"));
    }


    @Test
    void should_add_a_student_to_a_class() throws Exception {
        Student student = new Student(1, "zhang san", 15, 3);

        mockMvc.perform(post("/api/classes/3/students").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(student))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentName").value("zhang san"))
                .andExpect(jsonPath("$.classId").value(3));

    }

    @Test
    void should_find_all_the_students_of_a_class() throws Exception {
        Student student1 = new Student(1, "wang wu", 18, 1);
        Student student2 = new Student(2, "xiao ming", 16, 2);
        Student student3 = new Student(3, "xiao hong", 15, 3);
        Student student4 = new Student(4, "xiao wang", 15, 5);
        Student student5 = new Student(5, "xiao li", 21, 5);
        Student student6 = new Student(6, "xiao hang", 23, 5);
        Student student7 = new Student(7, "xiao mi", 16, 5);

        StudentStorage.saveStudent(student1);
        StudentStorage.saveStudent(student2);
        StudentStorage.saveStudent(student3);
        StudentStorage.saveStudent(student4);
        StudentStorage.saveStudent(student5);
        StudentStorage.saveStudent(student6);
        StudentStorage.saveStudent(student7);

        mockMvc.perform(get("/api/classes/5/students")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].studentName").value("xiao wang"))
                .andExpect(jsonPath("$[1].studentName").value("xiao li"))
                .andExpect(jsonPath("$[2].studentName").value("xiao hang"))
                .andExpect(jsonPath("$[3].studentName").value("xiao mi"));
    }

    @Test
    void should_find_students_whose_age_is_larger_than_20_in_class_5() throws Exception {
        mockMvc.perform(get("/api/classes/5/students?age=20")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].studentName").value("xiao li"))
                .andExpect(jsonPath("$[1].studentName").value("xiao hang"));
    }
}

