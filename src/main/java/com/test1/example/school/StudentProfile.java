package com.test1.example.school;

import com.test1.example.student.Student;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class StudentProfile {

    @Id
    @GeneratedValue
    private Integer id;

    private String bio;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
