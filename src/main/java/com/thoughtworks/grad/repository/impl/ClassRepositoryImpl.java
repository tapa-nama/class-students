package com.thoughtworks.grad.repository.impl;

import com.thoughtworks.grad.domain.ClassRoom;
import com.thoughtworks.grad.repository.ClassRepository;
import com.thoughtworks.grad.repository.ClassStorage;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ClassRepositoryImpl implements ClassRepository {
    @Override
    public Collection<ClassRoom> findClasses() {
        return ClassStorage.findClasses();
    }


}
