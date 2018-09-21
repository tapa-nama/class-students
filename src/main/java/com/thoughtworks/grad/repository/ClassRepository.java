package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.domain.Student;

import java.util.Collection;

public interface ClassRepository {
    Collection<ClassRoom> findClasses();

    ClassRoom addStudent(int classId, Student student);
}
