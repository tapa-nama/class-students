package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentStorage {
    private static final Map<Integer, Student> STUDENTS = new HashMap<>();

    public static Collection<Student> findStudents() {
        return STUDENTS.values();
    }

    public static Student saveStudent(Student student) {
        STUDENTS.put(student.getStudentId(), student);
        return student;
    }

    public static Collection<Student> findStudentsByClassId(int classId) {
        ArrayList<Student> resultStudents = new ArrayList<>();
        for (Map.Entry<Integer, Student> entry : STUDENTS.entrySet()) {
            if (entry.getValue().getClassId() == classId) {
                resultStudents.add(entry.getValue());
            }
        }
        return resultStudents;
    }

    public static Student addStudentToClass(int classId, Student student) {
        STUDENTS.put(student.getStudentId(), student);
        return student;
    }

    public static Collection<Student> findStudentsByClassIdAndAge(int classId, Integer age) {
        ArrayList<Student> resultStudents = new ArrayList<>();
        for (Map.Entry<Integer, Student> entry : STUDENTS.entrySet()) {
            if (entry.getValue().getClassId() == classId && entry.getValue().getAge() > 20) {
                resultStudents.add(entry.getValue());
            }
        }
        return resultStudents;
    }
}
