package com.thoughtworks.grad.repository;

import com.thoughtworks.grad.domain.ClassRoom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClassStorage {

    private static final Map<Integer, ClassRoom> CLASSES = new HashMap<>();

    public static Collection<ClassRoom> findClasses() {
        return CLASSES.values();
    }


    public static ClassRoom save(ClassRoom classRoom) {
        CLASSES.put(classRoom.getClassId(), classRoom);
        return CLASSES.get(classRoom.getClassId());
    }
}
