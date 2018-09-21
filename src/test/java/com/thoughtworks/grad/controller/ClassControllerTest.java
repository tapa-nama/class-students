package com.thoughtworks.grad.controller;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.repository.ClassStorage;
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
}
