package com.thoughtworks.grad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.domain.Student;
import com.thoughtworks.grad.repository.ClassStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ClassControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(controller()).build();

    }

    private ClassController controller() {
        return new ClassController();
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

 /*   @Test
    void should_find_a_class() throws Exception {
        ClassStorage.save(new ClassRoom(1, "class1"));
        ClassStorage.save(new ClassRoom(2, "class2"));
        ClassStorage.save(new ClassRoom(3, "class3"));
        ClassStorage.save(new ClassRoom(4, "class4"));

        mockMvc.perform(get("/api/classes/3")).andExpect(status().isOk())
                .andExpect(jsonPath())
    }*/

    @Test
    void should_add_a_student_to_a_class() throws Exception {
        Student student1 = new Student(1, "zhang san", 15);
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);

        ClassStorage.save(new ClassRoom(3, "class3", students));
        Student student2 = new Student(2, "li si", 16);

        mockMvc.perform(post("/api/classes/3/students").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(student2))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.classId").value(3))
                .andExpect(jsonPath("$.className").value("class3"))
                .andExpect(jsonPath("$.students[0].studentId").value(1))
                .andExpect(jsonPath("$.students[0].studentName").value("zhang san"))
                .andExpect(jsonPath("$.students[0].age").value(15));

    }
}

