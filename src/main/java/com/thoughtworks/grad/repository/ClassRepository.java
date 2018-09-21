package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.ClassRoom;

import java.util.Collection;

public interface ClassRepository {
    Collection<ClassRoom> findClasses();
}
