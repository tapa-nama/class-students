package com.thoughtworks.grad.controller;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.domain.Student;
import com.thoughtworks.grad.repository.ClassRepository;
import com.thoughtworks.grad.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ClassController {
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/api/classes")
    public Collection<ClassRoom> queryClasses() {
        return classRepository.findClasses();
    }

    @PostMapping("/api/classes/{classId}/students")
    public ResponseEntity addStudentToClass(@PathVariable int classId, @RequestBody Student student) {
        return new ResponseEntity(studentRepository.addStudentToClass(classId, student), HttpStatus.CREATED);
    }

    @GetMapping("/api/classes/{classId}/students")
    public Collection<Student> findAllStudentsOfAClass(@PathVariable int classId, @RequestParam(value = "age",required = false) Integer age) {
        if (age == null) {
            return studentRepository.findStudentsByClassId(classId);
        }
        return studentRepository.findStudentsByClassIdAndAge(classId, age);

    }




}
