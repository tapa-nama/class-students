package com.thoughtworks.grad.repository.impl;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.repository.ClassRepository;
import com.thoughtworks.grad.repository.ClassStorage;

import java.util.Collection;

public class ClassRepositoryImpl implements ClassRepository {
    @Override
    public Collection<ClassRoom> findClasses() {
        return ClassStorage.findClasses();
    }
}
