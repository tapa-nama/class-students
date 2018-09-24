package com.thoughtworks.grad.repository.impl;

import com.thoughtworks.grad.domain.Student;
import com.thoughtworks.grad.repository.StudentRepository;
import com.thoughtworks.grad.repository.StudentStorage;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public Collection<Student> findStudents() {
        return StudentStorage.findStudents();
    }

    @Override
    public Collection<Student> findStudentsByClassId(int classId) {
        return StudentStorage.findStudentsByClassId(classId);
    }

    @Override
    public Student addStudentToClass(int classId, Student student) {
        return StudentStorage.addStudentToClass(classId, student);
    }

    @Override
    public Collection<Student> findStudentsByClassIdAndAge(int classId, Integer age) {
        return StudentStorage.findStudentsByClassIdAndAge(classId, age);
    }
}
