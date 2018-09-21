package com.thoughtworks.grad.controller;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.domain.Student;
import com.thoughtworks.grad.repository.ClassStorage;
import com.thoughtworks.grad.repository.StudentStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

/*    @Test
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
                .andExpect(jsonPath("$.students[1].studentId").value(2))
                .andExpect(jsonPath("$.students[1].studentName").value("li si"))
                .andExpect(jsonPath("$.students[1].age").value(16));*/
//}

    @Test
    void should_find_all_the_students_of_a_class() throws Exception {
        Student student1 = new Student(1, "wang wu", 18, 1);
        Student student2 = new Student(2, "xiao ming", 16, 2);
        Student student3 = new Student(3, "xiao hong", 15, 3);
        Student student4 = new Student(4, "xiao wang", 15, 5);
        Student student5 = new Student(5, "xiao li", 15, 5);
        Student student6 = new Student(6, "xiao hang", 15, 5);
        Student student7 = new Student(7, "xiao mi", 15, 5);

        StudentStorage.save(student1);
        StudentStorage.save(student2);
        StudentStorage.save(student3);
        StudentStorage.save(student4);
        StudentStorage.save(student5);
        StudentStorage.save(student6);
        StudentStorage.save(student7);

        mockMvc.perform(get("/api/classes/5/students")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].studentName").value("xiao wang"))
                .andExpect(jsonPath("$[1].studentName").value("xiao li"))
                .andExpect(jsonPath("$[2].studentName").value("xiao hang"))
                .andExpect(jsonPath("$[3].studentName").value("xiao mi"));
    }
}

