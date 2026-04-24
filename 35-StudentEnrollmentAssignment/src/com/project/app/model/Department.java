package com.project.app.model;

public class Department {

    private int id;
    private String departmentName;

    public Department(int id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return id + " " + departmentName;
    }
}
