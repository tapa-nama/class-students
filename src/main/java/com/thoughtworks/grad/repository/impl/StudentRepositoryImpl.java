package com.thoughtworks.grad.repository.impl;

import com.thoughtworks.grad.domain.Student;
import com.thoughtworks.grad.repository.StudentRepository;
import com.thoughtworks.grad.repository.StudentStorage;

import java.util.Collection;

public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Collection<Student> findStudents() {
        return StudentStorage.findStudents();
    }

    @Override
    public Collection<Student> findStudentsByClassId(int classId) {
        return StudentStorage.findStudentsByClassId(classId);
    }
}
