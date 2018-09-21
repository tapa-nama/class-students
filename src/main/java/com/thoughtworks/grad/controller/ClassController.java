package com.thoughtworks.grad.controller;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.repository.ClassRepository;
import com.thoughtworks.grad.repository.impl.ClassRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ClassController {
    private ClassRepository classRepository = new ClassRepositoryImpl();

    @GetMapping("/api/classes")
    public Collection<ClassRoom> queryUser() {
        return classRepository.findClasses();
    }

}
