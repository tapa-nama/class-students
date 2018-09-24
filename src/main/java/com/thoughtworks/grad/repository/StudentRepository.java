package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.Student;

import java.util.Collection;

public interface StudentRepository {
    Collection<Student> findStudents();

    Collection<Student> findStudentsByClassId(int classId);

    Student addStudentToClass(int classId, Student student);
}
