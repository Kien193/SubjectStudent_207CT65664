package com.example.subjectstudent_207ct65664;

public class Student {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentHo() {
        return studentHo;
    }

    public void setStudentHo(String studentHo) {
        this.studentHo = studentHo;
    }

    public String getStudentTen() {
        return studentTen;
    }

    public void setStudentTen(String studentTen) {
        this.studentTen = studentTen;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    private int id;
    private String studentHo;
    private String studentTen;
    private String studentClass;

    public Student() {

    }

    public Student(int id, String studentHo, String studentTen, String studentClass) {
        this.id = id;
        this.studentHo = studentHo;
        this.studentTen = studentTen;
        this.studentClass = studentClass;
    }
}
