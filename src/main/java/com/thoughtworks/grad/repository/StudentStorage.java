package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentStorage {
    private static final Map<Integer, Student> STUDENTS = new HashMap<>();

    public static Collection<Student> findStudents() {
        return STUDENTS.values();
    }

    public static Student save(Student student) {
        STUDENTS.put(student.getStudentId(), student);
        return STUDENTS.get(student.getStudentId());
    }

}
