package com.thoughtworks.grad.domain;

import java.util.ArrayList;

public class ClassRoom {
    private int classId;
    private String className;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ClassRoom(int classId, String className, ArrayList<Student> students) {
        this.classId = classId;
        this.className = className;
        this.students = students;
    }

    private ArrayList<Student> students;

    public ClassRoom() {
    }

    public ClassRoom(int classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
