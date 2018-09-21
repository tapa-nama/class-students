package com.thoughtworks.grad.controller;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.domain.Student;
import com.thoughtworks.grad.repository.ClassRepository;
import com.thoughtworks.grad.repository.StudentRepository;
import com.thoughtworks.grad.repository.impl.ClassRepositoryImpl;
import com.thoughtworks.grad.repository.impl.StudentRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ClassController {
    private ClassRepository classRepository = new ClassRepositoryImpl();
    private StudentRepository studentRepository = new StudentRepositoryImpl();

    @GetMapping("/api/classes")
    public Collection<ClassRoom> queryUser() {
        return classRepository.findClasses();
    }


    @PostMapping("/api/classes/{classId}/students")
    public ResponseEntity addStudentToClass(@PathVariable int classId, @RequestBody Student student) {
        ClassRoom classRoom = classRepository.addStudent(classId, student);
        return new ResponseEntity(classRoom, HttpStatus.CREATED);

    }

    @GetMapping("/api/classes/{classId}/students")
    public Collection<Student> findAllStudentsOfAClass(@PathVariable int classId) {
        return studentRepository.findStudentsByClassId(classId);
    }


}
