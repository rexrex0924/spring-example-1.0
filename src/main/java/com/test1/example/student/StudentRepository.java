package com.test1.example.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<StudentResponseDTO> findAllByFirstnameContaining(String name);

}
